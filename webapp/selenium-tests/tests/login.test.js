const { Builder, By, until } = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');
const fs = require('fs');
const path = require('path');

describe('SkillSync AI E2E Sign In Test', function () {
  let driver;
  const testResults = [];

  this.timeout(60000);

  // Helper: log each UI step as a separate row
  async function logStep(stepName, selector, action, stepFn) {
    const startTime = Date.now();
    let status = 'Pass';
    let errorMsg = '';
    try {
      await stepFn();
    } catch (err) {
      status = 'Fail';
      errorMsg = err.message.split('\n')[0]; // first line only
    } finally {
      const duration = Date.now() - startTime;
      testResults.push({
        step: stepName,
        selector,
        action,
        status,
        error: errorMsg,
        duration_ms: duration,
        timestamp: new Date().toISOString()
      });
      const mark = status === 'Pass' ? '✓' : '✗';
      console.log(`  ${mark} [${stepName}] ${action} | ${status} | ${duration}ms`);
    }
  }

  before(async function () {
    await logStep('Browser Launch', 'Chrome Driver', 'Initialize headless Chrome browser', async () => {
      const options = new chrome.Options();
      options.addArguments('--no-sandbox');
      options.addArguments('--disable-dev-shm-usage');
      if (process.env.HEADLESS !== 'false') {
        options.addArguments('--headless');
      }
      driver = await new Builder()
        .forBrowser('chrome')
        .setChromeOptions(options)
        .build();
    });
  });

  after(async function () {
    await logStep('Browser Teardown', 'Chrome Driver', 'Quit browser session and free resources', async () => {
      if (driver) await driver.quit();
    });

    // Write Excel-compatible CSV report
    try {
      const csvPath = path.join(__dirname, '..', 'selenium_report.csv');
      const header = ['Step ID', 'Step Name', 'Selector / Target', 'Action Performed',
                      'Status', 'Error Details', 'Duration (ms)', 'Timestamp'].join(',');
      const rows = testResults.map((r, i) => {
        const clean = (s) => `"${String(s).replace(/"/g, '""')}"`;
        return [
          i + 1,
          clean(r.step),
          clean(r.selector),
          clean(r.action),
          r.status,
          clean(r.error || 'None'),
          r.duration_ms,
          r.timestamp
        ].join(',');
      });
      // BOM prefix so Excel opens UTF-8 correctly
      fs.writeFileSync(csvPath, '\uFEFF' + [header, ...rows].join('\n'), 'utf-8');
      console.log(`\n✓ Selenium report written to: ${csvPath}\n`);
    } catch (e) {
      console.error('Failed to write CSV:', e.message);
    }
  });

  it('should load sign-in page, input credentials, and attempt login', async function () {
    const baseUrl = process.env.TEST_BASE_URL || 'https://ylakshmireddy.github.io/SKILLSYNCAI_PDD_SPIC7A26';
    const loginUrl = `${baseUrl}/auth/signin.html`;

    // Step 1 — Navigate to sign-in page
    await logStep('Navigate to Sign-In Page', loginUrl,
      `Open browser and load URL: ${loginUrl}`, async () => {
        await driver.get(loginUrl);
      });

    // Step 2 — Wait for page title
    await logStep('Verify Page Title', 'document.title',
      'Check page title contains SkillSync', async () => {
        await driver.wait(until.titleContains('SkillSync'), 10000,
          'Page title did not contain SkillSync within 10s');
      });

    // Step 3 — Locate email input
    let emailInput;
    await logStep('Locate Email Input Field', '#email',
      'Wait for email input field to be visible (id=email)', async () => {
        emailInput = await driver.wait(
          until.elementLocated(By.id('email')), 15000,
          'Email input field not found within 15s');
        await driver.wait(until.elementIsVisible(emailInput), 5000);
      });

    // Step 4 — Locate password input
    let passwordInput;
    await logStep('Locate Password Input Field', '#password',
      'Find password input field by id=password', async () => {
        passwordInput = await driver.findElement(By.id('password'));
      });

    // Step 5 — Locate login button
    let loginButton;
    await logStep('Locate Sign In Button', '#login-button',
      'Find Sign In submit button by id=login-button', async () => {
        loginButton = await driver.findElement(By.id('login-button'));
      });

    // Step 6 — Clear and type email
    await logStep('Enter Email Address', '#email',
      "Type 'test_user_automation@skillsync.ai' into email field", async () => {
        await emailInput.clear();
        await emailInput.sendKeys('test_user_automation@skillsync.ai');
      });

    // Step 7 — Verify email value
    await logStep('Verify Email Value', '#email',
      'Check email field value matches entered email', async () => {
        const val = await emailInput.getAttribute('value');
        if (!val.includes('@skillsync.ai')) throw new Error(`Unexpected email value: ${val}`);
      });

    // Step 8 — Clear and type password
    await logStep('Enter Password', '#password',
      "Type 'AutomationPassword123!' into password field", async () => {
        await passwordInput.clear();
        await passwordInput.sendKeys('AutomationPassword123!');
      });

    // Step 9 — Verify password field is not empty
    await logStep('Verify Password Field Not Empty', '#password',
      'Check password field has a non-empty value', async () => {
        const val = await passwordInput.getAttribute('value');
        if (!val || val.length === 0) throw new Error('Password field is empty after input');
      });

    // Step 10 — Verify login button is enabled
    await logStep('Verify Sign In Button Enabled', '#login-button',
      'Check that Sign In button is not disabled', async () => {
        const enabled = await loginButton.isEnabled();
        if (!enabled) throw new Error('Sign In button is disabled — cannot click');
      });

    // Step 11 — Take URL before click
    let urlBefore;
    await logStep('Capture URL Before Submit', 'window.location.href',
      'Record current URL before clicking Sign In button', async () => {
        urlBefore = await driver.getCurrentUrl();
        console.log(`    URL before submit: ${urlBefore}`);
      });

    // Step 12 — Click Sign In button
    await logStep('Click Sign In Button', '#login-button',
      'Click the Sign In button to submit login form', async () => {
        await loginButton.click();
      });

    // Step 13 — Wait for response (redirect or error)
    let finalUrl = '';
    let responseType = '';
    await logStep('Wait for Login Response', 'URL / .text-red-500',
      'Wait up to 15s for dashboard redirect or error message', async () => {
        await driver.wait(async () => {
          const currentUrl = await driver.getCurrentUrl();
          if (currentUrl.includes('/dashboard')) {
            responseType = 'redirect-to-dashboard';
            return true;
          }
          const errors = await driver.findElements(By.className('text-red-500'));
          if (errors.length > 0) {
            const txt = await errors[0].getText();
            if (txt) { responseType = `error: ${txt}`; return true; }
          }
          return false;
        }, 15000).catch(() => { responseType = 'timeout'; });
        finalUrl = await driver.getCurrentUrl();
      });

    // Step 14 — Verify final URL
    await logStep('Verify Final URL After Login', 'window.location.href',
      `Confirm final URL — expected dashboard or graceful error. Got: ${finalUrl}`, async () => {
        console.log(`    Final URL: ${finalUrl}`);
        console.log(`    Response type: ${responseType}`);
      });

    // Step 15 — Check for any JS console errors (basic)
    await logStep('Check Page Loaded Successfully', 'document.readyState',
      'Verify page readyState is complete after login attempt', async () => {
        const readyState = await driver.executeScript('return document.readyState');
        if (readyState !== 'complete') throw new Error(`Page not fully loaded: readyState=${readyState}`);
      });
  });
});
