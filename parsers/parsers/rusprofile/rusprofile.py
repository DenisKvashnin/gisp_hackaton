"""
Модуль парсинга с https://www.rusprofile.ru/
"""

DEBUG = False

import random
import subprocess
import time
import sys
from pprint import pprint

from lxml import etree
import requests


client = requests.session()
cookies = {}
redirURL = ''
homeURL = 'https://www.rusprofile.ru/'
headers = {
'accept': 'application/json, text/javascript, */*; q=0.01',
'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.198 Safari/537.36'
}
client.get(homeURL, headers=headers) # Ставим куки
for cookie in client.cookies.get_dict().items():
    cookies[cookie[0]] = cookie[1]


def getEGRULInfo(code:str = '') -> str:
    """
    Получаем данные ЕГРЮЛ об активном юр.лице с таким ОГРН
    `code` - Код ОГРН
    `return` - JSON с информацией об организации
    """
    if code == '':
        return 'Empty EGRUL given', {}

    searchURL =  f'https://www.rusprofile.ru/egrul?ogrn={code}'
    response = requests.get(searchURL, headers=headers, cookies=cookies)
    # print(response.text)


    curl1 = f"curl 'https://www.rusprofile.ru/egrul?ogrn={code}' \
    -H 'authority: www.rusprofile.ru' \
    -H 'pragma: no-cache' \
    -H 'cache-control: no-cache' \
    -H 'sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"' \
    -H 'sec-ch-ua-mobile: ?0' \
    -H 'dnt: 1' \
    -H 'upgrade-insecure-requests: 1' \
    -H 'user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36' \
    -H 'accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
    -H 'sec-fetch-site: same-origin' \
    -H 'sec-fetch-mode: navigate' \
    -H 'sec-fetch-user: ?1' \
    -H 'sec-fetch-dest: document' \
    -H 'referer: https://www.rusprofile.ru/egrul?ogrn={code}' \
    -H 'accept-language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' \
    -H 'cookie: screen_for_ad=desktop; ab_no_ad=43; sessid=cf15ea59c2662ac7d109c53701be6736' \
    -H 'sec-gpc: 1' \
    --compressed"


    html_page = etree.HTML(subprocess.check_output(curl1, shell=True).decode('utf-8'))

    egrul_json = { "OGRN": code }
    company_page = ''

    try:
        company_page = homeURL[:-1] + html_page.xpath('/html/body/div[2]/div/div/div[1]/div[2]/nav/a[1]/@href')[0]
        temp = html_page.xpath('//html/body/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[2]/td[3]/text()')[0].split(' / ')
        egrul_json['INN'] = temp[0]
        egrul_json['KPP'] = temp[1]
        egrul_json['name_full'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[3]/table/tbody/tr[1]/td[3]/text()')[0]
        egrul_json['name_short'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[3]/table/tbody/tr[2]/td[3]/text()')[0]
        egrul_json['EGRUL_regdate'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[3]/table/tbody/tr[3]/td[3]/text()[2]')[0]
        egrul_json['adress_post_index'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[5]/table/tbody/tr[1]/td[3]/text()')[0]
        egrul_json['adress_subject'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[5]/table/tbody/tr[2]/td[3]/text()')[0]
        egrul_json['adress_street'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[5]/table/tbody/tr[3]/td[3]/text()')[0]
        egrul_json['adress_home'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[5]/table/tbody/tr[4]/td[3]/text()')[0]
        egrul_json['adress_home_corpus'] = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[5]/table/tbody/tr[5]/td[3]/text()')[0]
        temp = html_page.xpath('//*[@id="main"]/div/div[2]/div/div[23]/table[*]/tbody/tr[1]/td[3]/text()')
        egrul_json['OKVED'] = []
        for i in temp:
            egrul_json['OKVED'].append(i.split(' ')[0])
    except (IndexError, AttributeError) as e:
        print('1. Пробемы с парсингом данных. Они будут пропущены.')

    if DEBUG == False:
        time.sleep(random.random()*random.randint(0,4))

    if company_page == '':
        raise Exception('Empty company page string')

    print('>>', company_page)
    curl2 = f"curl '{company_page}' \
    -H 'authority: www.rusprofile.ru' \
    -H 'pragma: no-cache' \
    -H 'cache-control: no-cache' \
    -H 'sec-ch-ua: \" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Google Chrome\";v=\"90\"' \
    -H 'sec-ch-ua-mobile: ?0' \
    -H 'upgrade-insecure-requests: 1' \
    -H 'dnt: 1' \
    -H 'user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36' \
    -H 'accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
    -H 'sec-fetch-site: same-origin' \
    -H 'sec-fetch-mode: navigate' \
    -H 'sec-fetch-user: ?1' \
    -H 'sec-fetch-dest: document' \
    -H 'referer: {searchURL}' \
    -H 'accept-language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' \
    -H 'cookie: screen_for_ad=desktop; ab_no_ad=43; sessid=cf15ea59c2662ac7d109c53701be6736' \
    -H 'sec-gpc: 1' \
    --compressed"

    html_page = etree.HTML(subprocess.check_output(curl2, shell=True).decode('utf-8'))

    try:
        egrul_json['people_count'] = html_page.xpath('//*[@id="anketa"]/div[2]/div[1]/dl[1]/dd/span/text()')[0].replace('\n','').split()[0]
    except (IndexError, AttributeError) as e:
        print('2. Пробемы с парсингом данных. Они будут пропущены.')

    return egrul_json
