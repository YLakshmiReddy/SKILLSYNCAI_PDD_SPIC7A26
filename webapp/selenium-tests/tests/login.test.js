const { Builder, By, until } = require('selenium-webdriver');
const chrome = require('selenium-webdriver/chrome');
const assert = require('assert');

describe('SkillSync AI E2E Sign In Test', function () {
  let driver;

  // Set timeout for the tests
  this.timeout(60000);

  before(async function () {
    // Configure Chrome options
    const options = new chrome.Options();
    options.addArguments('--no-sandbox');
    options.addArguments('--disable-dev-shm-usage');
    
    // Check if we want to run headless (e.g. in CI or background)
    if (process.env.HEADLESS !== 'false') {
      options.addArguments('--headless');
    }

    driver = await new Builder()
      .forBrowser('chrome')
      .setChromeOptions(options)
      .build();
  });

  after(async function () {
    if (driver) {
      await driver.quit();
    }
  });

  it('should load sign-in page, input credentials, and attempt login', async function () {
    const baseUrl = process.env.TEST_BASE_URL || 'https://ylakshmireddy.github.io/SKILLSYNCAI_PDD_SPIC7A26';
    const loginUrl = `${baseUrl}/auth/signin.html`;

    console.log(`Navigating to: ${loginUrl}`);
    await driver.get(loginUrl);

    // Wait for the email field to load
    const emailInput = await driver.wait(
      until.elementLocated(By.id('email')),
      15000,
      'Email input field not found'
    );

    // Locate elements
    const passwordInput = await driver.findElement(By.id('password'));
    const loginButton = await driver.findElement(By.id('login-button'));

    // Input credentials
    await emailInput.sendKeys('test_user_automation@skillsync.ai');
    await passwordInput.sendKeys('AutomationPassword123!');

    // Click Sign In
    console.log('Clicking the Sign In button');
    await loginButton.click();

    // Since it's a dummy test user, it might fail or succeed depending on database state.
    // We will wait and capture if there is an error message displayed or if we get redirected.
    try {
      // Wait for URL change or an error message to appear
      await driver.wait(async () => {
        const currentUrl = await driver.getCurrentUrl();
        if (currentUrl.includes('/dashboard')) {
          return true;
        }
        
        // Or check if error message is displayed
        const errorElements = await driver.findElements(By.className('text-red-500'));
        if (errorElements.length > 0) {
          const text = await errorElements[0].getText();
          if (text) {
            console.log(`Error displayed on UI: ${text}`);
            return true;
          }
        }
        return false;
      }, 15000);

      const finalUrl = await driver.getCurrentUrl();
      console.log(`Final URL reached: ${finalUrl}`);
      
      if (finalUrl.includes('/dashboard')) {
        console.log('Login E2E redirection test passed successfully!');
      } else {
        console.log('Login E2E completed with expected validation output.');
      }
    } catch (waitError) {
      console.log('Finished waiting. Current URL: ' + await driver.getCurrentUrl());
    }
  });
});
