package com.homecoming.app.baselineprofile

import androidx.benchmark.macro.junit4.BaselineProfileRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val PACKAGE = "com.homecoming.app"
private const val WAIT_MS = 5_000L

@RunWith(AndroidJUnit4::class)
@LargeTest
class BaselineProfileGenerator {

    @get:Rule
    val baselineProfileRule = BaselineProfileRule()

    @Test
    fun startup() = baselineProfileRule.collect(
        packageName = PACKAGE,
        includeInStartupProfile = true,
    ) {
        pressHome()
        startActivityAndWait()
        device.wait(Until.hasObject(By.text("HomeComing")), WAIT_MS)
    }

    @Test
    fun criticalUserJourneys() = baselineProfileRule.collect(
        packageName = PACKAGE,
    ) {
        pressHome()
        startActivityAndWait()
        device.wait(Until.hasObject(By.text("HomeComing")), WAIT_MS)

        device.findObject(By.desc("Events"))?.click()
        device.wait(Until.hasObject(By.text("Events")), WAIT_MS)

        device.findObject(By.desc("Profile"))?.click()
        device.wait(Until.hasObject(By.text("Profile")), WAIT_MS)

        device.findObject(By.desc("Home"))?.click()
        device.wait(Until.hasObject(By.text("HomeComing")), WAIT_MS)

        device.findObject(By.text("View schedule"))?.click()
        device.wait(Until.hasObject(By.text("Opening ceremony")), WAIT_MS)
    }
}
