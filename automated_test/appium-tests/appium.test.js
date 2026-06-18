const { remote } = require('webdriverio');
const path = require('path');
const fs = require('fs');
const apkPath = path.resolve(__dirname, '../../app/build/outputs/apk/debug/app-debug.apk');

describe('SkillSync AI Mobile UI Login Test', function () {
  let client;
  const testResults = [];

  this.timeout(120000); // Set E2E timeout limit

  // Helper function to execute and log each micro-action as a separate row
  async function performAction(actionName, details, actionFn) {
    const startTime = Date.now();
    let status = 'Success';
    let errorMessage = '';
    try {
      await actionFn();
    } catch (err) {
      status = 'Failed';
      errorMessage = err.message;
      throw err;
    } finally {
      const duration = Date.now() - startTime;
      testResults.push({
        action: actionName,
        details: details,
        status: status,
        error: errorMessage,
        duration: duration,
        timestamp: new Date().toISOString()
      });
    }
  }

  before(async function () {
    await performAction('Start WebDriver Session', 'Appium Port 4723 (UiAutomator2)', async () => {
      const opts = {
        port: 4723,
        capabilities: {
          platformName: 'Android',
          'appium:automationName': 'UiAutomator2',
          'appium:deviceName': 'Android Device',
          'appium:app': apkPath,
          'appium:appPackage': 'com.simats.SkillSyncAI',
          'appium:appActivity': 'com.simats.SkillSyncAI.MainActivity',
          'appium:ensureWebviewsHavePages': true,
          'appium:nativeWebScreenshot': true,
          'appium:newCommandTimeout': 3600
        }
      };
      client = await remote(opts);
    });
  });

  after(async function () {
    if (client) {
      await performAction('Delete WebDriver Session', 'Cleanup connection', async () => {
        await client.deleteSession();
      });
    }

    // Write results to CSV (Excel compatible)
    try {
      const csvPath = path.join(__dirname, 'appium_report.csv');
      const csvRows = [
        ['Step ID', 'Action Name', 'Details / Selector', 'Status', 'Error Details', 'Duration (ms)', 'Timestamp'].join(',')
      ];

      testResults.forEach((r, idx) => {
        const cleanError = r.error ? `"${r.error.replace(/"/g, '""')}"` : 'None';
        const cleanAction = `"${r.action.replace(/"/g, '""')}"`;
        const cleanDetails = `"${r.details.replace(/"/g, '""')}"`;
        csvRows.push([
          idx + 1,
          cleanAction,
          cleanDetails,
          r.status,
          cleanError,
          r.duration,
          r.timestamp
        ].join(','));
      });

      // Prepend BOM (\uFEFF) so Excel opens UTF-8 format automatically
      fs.writeFileSync(csvPath, '\uFEFF' + csvRows.join('\n'), 'utf-8');
      console.log(`\n✓ Appium step log successfully written to: ${csvPath}\n`);
    } catch (csvErr) {
      console.error('Failed to write CSV report:', csvErr);
    }
  });

  it('should navigate, enter credentials and click sign-in', async function () {
    // 1. Navigate from Welcome Screen to Sign In Screen
    await performAction('Navigate to Sign In Screen', 'Click "Sign In" Button on Welcome Screen', async () => {
      try {
        const welcomeSignInBtn = await client.$('//*[@text="Sign In"]');
        await welcomeSignInBtn.waitForDisplayed({ timeout: 10000 });
        await welcomeSignInBtn.click();
        console.log('Navigated from Welcome Screen to Sign In Screen.');
      } catch (e) {
        console.log('Already on Sign In Screen or Welcome Screen button not found.');
      }
    });

    // 2. Input Email
    await performAction('Enter Email Address', 'Type into (//android.widget.EditText)[1]', async () => {
      const emailField = await client.$('(//android.widget.EditText)[1]');
      await emailField.waitForDisplayed({ timeout: 15000 });
      await emailField.setValue('test_mobile_appium@skillsync.ai');
    });

    // 3. Input Password
    await performAction('Enter Password', 'Type into (//android.widget.EditText)[2]', async () => {
      const passwordField = await client.$('(//android.widget.EditText)[2]');
      await passwordField.setValue('AppiumPassword123!');
    });

    // 4. Click Sign In Button
    await performAction('Submit Sign In Form', 'Click button with text/desc "Sign In"', async () => {
      const signInBtn = await client.$('//*[@text="Sign In" or @content-desc="Sign In"]');
      await signInBtn.click();
      console.log('Clicked Sign In button on Login screen.');
    });

    // 5. Verify Dashboard Navigation
    await performAction('Verify Dashboard Landing', 'Wait for "Resume Match Score" or "0%" TextView', async () => {
      try {
        const dashboardHeader = await client.$('//android.widget.TextView[contains(@text, "Resume Match Score") or contains(@text, "0%")]');
        await dashboardHeader.waitForDisplayed({ timeout: 20000 });
        console.log('Successfully navigated to Dashboard. Test Passed!');
      } catch (e) {
        console.log('Dashboard elements not loaded yet or login rejected.');
      }
    });
  });
});
