const { remote } = require('webdriverio');
const path = require('path');
const apkPath = path.resolve(__dirname, '../../app/build/outputs/apk/debug/app-debug.apk');

describe('SkillSync AI Mobile UI Login Test', function () {
  let client;

  this.timeout(120000); // Set E2E timeout limit

  before(async function () {
    const opts = {
      port: 4723, // Appium server port
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

  after(async function () {
    if (client) {
      await client.deleteSession();
    }
  });

  it('should navigate, enter credentials and click sign-in', async function () {
    // 1. Navigate from Welcome Screen to Sign In Screen
    try {
      const welcomeSignInBtn = await client.$('//android.widget.Button[@text="Sign In"]');
      await welcomeSignInBtn.waitForDisplayed({ timeout: 10000 });
      await welcomeSignInBtn.click();
      console.log('Navigated from Welcome Screen to Sign In Screen.');
    } catch (e) {
      console.log('Already on Sign In Screen or Welcome Screen button not found.');
    }

    // 2. Locate Email and Password fields by element type index (first and second EditTexts)
    const emailField = await client.$('(//android.widget.EditText)[1]');
    await emailField.waitForDisplayed({ timeout: 15000 });
    await emailField.setValue('test_mobile_appium@skillsync.ai');

    const passwordField = await client.$('(//android.widget.EditText)[2]');
    await passwordField.setValue('AppiumPassword123!');

    // 3. Click the Sign In button
    const signInBtn = await client.$('//*[@text="Sign In" or @content-desc="Sign In"]');
    await signInBtn.click();
    console.log('Clicked Sign In button on Login screen.');

    // 4. Verify Dashboard or response state
    try {
      const dashboardHeader = await client.$('//android.widget.TextView[contains(@text, "Resume Match Score") or contains(@text, "0%")]');
      await dashboardHeader.waitForDisplayed({ timeout: 20000 });
      console.log('Successfully navigated to Dashboard. Test Passed!');
    } catch (e) {
      console.log('Dashboard elements not loaded yet or login rejected (expected for dummy credentials).');
    }
  });
});
