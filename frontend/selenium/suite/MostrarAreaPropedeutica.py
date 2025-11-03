# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class MostrarAreaPropedeutica(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome(executable_path=r'')
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.google.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_mostrar_area_propedeutica(self):
        driver = self.driver
        driver.get("http://127.0.0.1:5500/CrearGrupo/CrearNuevoGrupo.html")
        driver.find_element_by_id("group-letter").click()
        driver.find_element_by_id("group-letter").clear()
        driver.find_element_by_id("group-letter").send_keys("A")
        driver.find_element_by_id("turno").click()
        Select(driver.find_element_by_id("turno")).select_by_visible_text("Vespertino")
        driver.find_element_by_id("semestre").click()
        Select(driver.find_element_by_id("semestre")).select_by_visible_text("2")
        driver.find_element_by_id("semestre").click()
        Select(driver.find_element_by_id("semestre")).select_by_visible_text("3")
        driver.find_element_by_id("semestre").click()
        Select(driver.find_element_by_id("semestre")).select_by_visible_text("4")
        driver.find_element_by_id("semestre").click()
        Select(driver.find_element_by_id("semestre")).select_by_visible_text("5")
        driver.find_element_by_id("semestre").click()
        Select(driver.find_element_by_id("semestre")).select_by_visible_text("6")
        driver.find_element_by_id("carrera").click()
        Select(driver.find_element_by_id("carrera")).select_by_visible_text(u"Técnico en Soporte y Mantenimiento de Equipo de Cómputo")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text(u"Informática y Computación")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text(u"Físico-Matemática")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text(u"Químico-Biológica")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text(u"Económico-Administrativa")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text("Humanidades y Ciencias Sociales")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text(u"Artes y Diseño")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text("Agropecuaria")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text("Industrial")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text(u"Turismo y Gastronomía")
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text(u"Educación y Pedagogía")
    
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
