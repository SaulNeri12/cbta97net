# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class IngresarCaracteresInvalidosEnLetraDelGrupo(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome(executable_path=r'')
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.google.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_ingresar_caracteres_invalidos_en_letra_del_grupo(self):
        driver = self.driver
        driver.get("http://127.0.0.1:5500/CrearGrupo/CrearNuevoGrupo.html")
        driver.find_element_by_id("group-letter").click()
        driver.find_element_by_id("group-letter").clear()
        driver.find_element_by_id("group-letter").send_keys("5")
        driver.find_element_by_id("group-name").click()
        driver.find_element_by_id("group-name").clear()
        driver.find_element_by_id("group-name").send_keys("a")
        driver.find_element_by_xpath("//div[@id='materias-list']/div").click()
        driver.find_element_by_id("horario-dia").click()
        Select(driver.find_element_by_id("horario-dia")).select_by_visible_text("Jueves")
        driver.find_element_by_id("horario-inicio").click()
        Select(driver.find_element_by_id("horario-inicio")).select_by_visible_text("7:00")
        driver.find_element_by_id("horario-fin").click()
        Select(driver.find_element_by_id("horario-fin")).select_by_visible_text("12:00")
        driver.find_element_by_id("modal-docente").click()
        Select(driver.find_element_by_id("modal-docente")).select_by_visible_text("Carlos Gomez Lopez")
        driver.find_element_by_id("modal-aula").click()
        Select(driver.find_element_by_id("modal-aula")).select_by_visible_text("AV-105")
        driver.find_element_by_id("add-horario-btn").click()
        driver.find_element_by_id("confirm-btn").click()
        self.assertEqual("Clase asignada correctamente al horario", self.close_alert_and_get_its_text())
        driver.find_element_by_id("create-group-btn").click()
        self.assertEqual(u"Debe ingresar una letra válida para el grupo.", self.close_alert_and_get_its_text())
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True
    
    def is_alert_present(self):
        try: self.driver.switch_to_alert()
        except NoAlertPresentException as e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to_alert()
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
