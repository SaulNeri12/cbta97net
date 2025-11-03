# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException, NoAlertPresentException
from selenium.webdriver.chrome.service import Service
import unittest, time, re

class CicloEscolarActivo(unittest.TestCase):
    def setUp(self):
        service = Service("chromedriver.exe")  
        self.driver = webdriver.Chrome(service=service)
        self.driver.implicitly_wait(30)
        self.base_url = "https://www.google.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_ciclo_escolar_activo(self):
        driver = self.driver
        driver.get("http://127.0.0.1:5500/CrearGrupo/CrearNuevoGrupo.html")

        time.sleep(2)

        if self.is_alert_present():
            alert_text = self.close_alert_and_get_its_text()
            print("Texto del alert:", alert_text)
            self.assertIn("Clase asignada correctamente al horario", alert_text)

    def is_element_present(self, how, what):
        try:
            self.driver.find_element(by=how, value=what)
            return True
        except NoSuchElementException:
            return False
    
    def is_alert_present(self):
        try:
            self.driver.switch_to.alert
            return True
        except NoAlertPresentException:
            return False
    
    def close_alert_and_get_its_text(self):
        try:
            alert = self.driver.switch_to.alert
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally:
            self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()
