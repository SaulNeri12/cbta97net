import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.testobject.SelectorMethod

import com.thoughtworks.selenium.Selenium
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.WebDriver
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium
import static org.junit.Assert.*
import java.util.regex.Pattern
import static org.apache.commons.lang3.StringUtils.join
import org.testng.asserts.SoftAssert
import com.kms.katalon.core.testdata.CSVData
import org.openqa.selenium.Keys as Keys

SoftAssert softAssertion = new SoftAssert();
WebUI.openBrowser('https://www.google.com/')
def driver = DriverFactory.getWebDriver()
String baseUrl = "https://www.google.com/"
selenium = new WebDriverBackedSelenium(driver, baseUrl)
selenium.open("http://127.0.0.1:5500/CrearGrupo/CrearNuevoGrupo.html")
selenium.click("id=group-letter")
selenium.type("id=group-letter", "m")
selenium.click("id=group-name")
selenium.type("id=group-name", ("facebook.com").toString())
selenium.click("id=semestre")
selenium.select("id=semestre", "label=5")
selenium.click("id=semestre")
selenium.select("id=semestre", "label=6")
selenium.click("id=carrera")
selenium.select("id=carrera", "label=Técnico en Soporte y Mantenimiento de Equipo de Cómputo")
selenium.click("id=area-propedeutica")
selenium.select("id=area-propedeutica", "label=Informática y Computación")
selenium.click("xpath=//div[@id='materias-list']/div[2]")
selenium.click("id=horario-dia")
selenium.select("id=horario-dia", "label=Martes")
selenium.click("id=horario-inicio")
selenium.select("id=horario-inicio", "label=7:00")
selenium.click("id=horario-fin")
selenium.select("id=horario-fin", "label=11:00")
selenium.click("id=modal-docente")
selenium.select("id=modal-docente", "label=Ana Martinez Sanchez")
selenium.click("id=modal-aula")
selenium.select("id=modal-aula", "label=AV-115")
selenium.click("id=add-horario-btn")
selenium.click("id=confirm-btn")
selenium.click("id=custom-modal-ok")
selenium.click("id=create-group-btn")
