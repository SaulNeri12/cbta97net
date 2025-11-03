from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

import time 


service = Service(executable_path = "chromedriver.exe")
driver = webdriver.Chrome(service=service)


driver.get("https://google.com")

# espera a que llegue el elemento html a que renderice, si renderiza ese elemento
# 
WebDriverWait(driver, 5).until(
    EC.presence_of_element_located((By.CLASS_NAME, "gLFyf"))
)
# clas namme de html y el nombre de la clase
input_element = driver.find_element(By.CLASS_NAME, "gLFyf")
# clear element html
input_element.clear()
#  XD
input_element.send_keys("tech with tim" + Keys.ENTER)

WebDriverWait(driver, 5).until(
    EC.presence_of_element_located((By.PARTIAL_LINK_TEXT, "Tech With Tim"))
)
# en cualquier html element si existe algo con este texto, haz clicl
link = driver.find_element(By.PARTIAL_LINK_TEXT, "Tech With Tim")
link.click()





time.sleep(10)

driver.quit()
