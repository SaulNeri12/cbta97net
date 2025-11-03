# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class VisualizarMateriasDisponiblesPorSemestreCarreraTCnicaYReaPropedUtica(unittest.TestCase):
    def setUp(self):
        self.driver = webdriver.Chrome(executable_path=r'')
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.google.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_visualizar_materias_disponibles_por_semestre_carrera_t_cnica_y_rea_proped_utica(self):
        driver = self.driver
        driver.get("http://127.0.0.1:5500/CrearGrupo/CrearNuevoGrupo.html")
        driver.find_element_by_id("group-letter").click()
        driver.find_element_by_id("group-letter").clear()
        driver.find_element_by_id("group-letter").send_keys("A")
        driver.find_element_by_id("group-name").click()
        driver.find_element_by_id("group-name").clear()
        driver.find_element_by_id("group-name").send_keys("A")
        driver.find_element_by_id("semestre").click()
        driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='Nota del grupo'])[1]/following::label[1]").click()
        driver.find_element_by_id("turno").click()
        driver.find_element_by_id("semestre").click()
        Select(driver.find_element_by_id("semestre")).select_by_visible_text("5")
        driver.find_element_by_id("semestre").click()
        Select(driver.find_element_by_id("semestre")).select_by_visible_text("6")
        driver.find_element_by_id("carrera").click()
        Select(driver.find_element_by_id("carrera")).select_by_visible_text(u"Técnico Agropecuario")
        driver.find_element_by_xpath("(.//*[normalize-space(text()) and normalize-space(.)='Crear Nuevo Grupo'])[2]/following::form[1]").click()
        driver.find_element_by_id("area-propedeutica").click()
        Select(driver.find_element_by_id("area-propedeutica")).select_by_visible_text("Agropecuaria")
        driver.find_element_by_xpath("//div[@id='materias-list']/div[3]").click()
    
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
