# 참고 사이트
# 1949773
# https://beomi.github.io/2017/07/05/HowToMakeWebCrawler-with-Multiprocess/

from urllib import request
import requests
import csv
import ssl
from bs4 import BeautifulSoup as bs
import selenium
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
from multiprocessing import Pool
from csv import writer
import os
import random
# 크롤링에서 사용하는 변수들
headers = { 'user-agent':'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.109 Safari/537.36'}
baseUrl = "https://map.kakao.com/";
page = 1

def writeCSV(list):
    list_title = []
    # ===================================================
    if os.path.isfile("gym_busan.csv"):
        pass
    else:
        with open('gym_busan.csv', 'w', newline='', encoding='utf-8-sig') as f_object:
            writer_object = writer(f_object)
            writer_object.writerow(list_title)
            f_object.close()

    with open('gym_busan.csv', 'a', newline='', encoding='utf-8-sig') as f_object:
        writer_object = writer(f_object)
        for data in list:
            writer_object.writerow(data)
        f_object.close()

data = []
region = '부산'
addr = ['금정구', '중구', '서구', '동구', '영도구', '부산진구', '동래구', '남구', '북구', '해운대구', '사하구', '강서구', '연제구', '수영구', '사상구', '기장군']
category = ['축구']

ad = []
for i in addr:
    for j in category:
        ad.append(region+' '+i+' '+j)

def get_information():
#     global headers

#     color = []
#     colorUrl = "https://www.musinsa.com/category/001"
#     colorResponse = requests.get(colorUrl, headers=headers)
#     colorHtml = colorResponse.text
#     colorSoup = bs(colorHtml, 'html.parser')
#     colorUl = colorSoup.find('ul', class_="division_color")
#     colorLi = colorUl.find_all('a')
#     for col in colorLi:
#         color.append(col['data-code'])
#     color = color[34:]


#     link = []
#     for colorNo in color:
#         pageNo = 1
#         # ===================================================
#         url = "https://www.musinsa.com/category/001?&brand=&rate=&page_kind=search&list_kind=small&sort=pop&sub_sort=&page="+str(pageNo)+"&display_cnt=90&sale_goods=&group_sale=&kids=N&ex_soldout=&color="+colorNo+"&price1=&price2=&exclusive_yn=&shoeSizeOption=&tags=&campaign_id=&timesale_yn=&q=&includeKeywords=&measure="
#         response = requests.get(url, headers=headers)
#         html = response.text
#         soup = bs(html, 'html.parser')
#         if soup.find('span', class_="totalPagingNum"):
#             totalpageNo = soup.find('span', class_="totalPagingNum").text
#             totalpageNo = int(totalpageNo)
#             while True:
#                 if pageNo == totalpageNo:
#                     link.append(url)
#                     break
#                 else:
#                     link.append(url)
#                     pageNo += 1
#                     url = "https://www.musinsa.com/category/001?&brand=&rate=&page_kind=search&list_kind=small&sort=pop&sub_sort=&page="+str(pageNo)+"&display_cnt=90&sale_goods=&group_sale=&kids=N&ex_soldout=&color="+colorNo+"&price1=&price2=&exclusive_yn=&shoeSizeOption=&tags=&campaign_id=&timesale_yn=&q=&includeKeywords=&measure="
#         else:
#             break
#     print('link끝')
    return link

# print(get_clothes())

def crawling(search):
    global headers
    total_data = []
    options = webdriver.ChromeOptions()
    options.add_argument('lang=ko_KR') #  KR 언어
    chromedriver_path = "chromedriver"
    driver = webdriver.Chrome(os.path.join(os.getcwd(), chromedriver_path), options=options)
    driver.get(baseUrl)
    search_area = driver.find_element_by_xpath('//*[@id="search.keyword.query"]')
    search_area.send_keys(search)
    driver.find_element_by_xpath('//*[@id="search.keyword.submit"]').send_keys(Keys.ENTER)
    time.sleep(random.uniform(1, 2))
    html = driver.page_source
    soup = bs(html, 'html.parser')
    page_list = soup.find("ul", class_="placelist")
    for page in page_list:
        title = page.find('span', class_="screen_out").contents[0]
        category = search.split()[-1]
        rate = page.find('em', class_="num").contents[0]
        # address = page.find('p', {'data-id': "address"}).contents[0]
        # phone = page.find('span', class_="phone").contents[0]
        new_url_no = page.find('a', class_="moreview")['href'].split('/')[-1]
        new_url = "https://place.map.kakao.com/"+str(new_url_no)
        new_response = requests.get(new_url, headers=headers)
        print(new_response)
        new_html = new_response.text
        time.sleep(random.uniform(1, 2))
        new_soup = bs(new_html, 'html.parser')
        print(new_soup)
        address = new_soup.find('span', class_='txt_address').contents[0]
        when, contact = 0, 0
        if new_soup.find('span', class_="txt_caution"):
            when = new_soup.find('span', class_="txt_caution").contents[0]
        if new_soup.find('span', class_="txt_contact"):
            contact = new_soup.find('span', class_="txt_contact").contents[0]
        facility_list = new_soup.find('ul', class_="list_facility")
        facilities = []
        for i in facility_list:
            print(i)
        break
    # print(soup)
    # searchBox = soup.find("div", id="_pcmap_list_scroll_container")
    # print(searchBox)
    # totalClothes = searchBox.find_all("li", class_="_22p-O _2NEjP")
    # for cloth in totalClothes:
    #     print(cloth)
        # try:
            
            # data = []
            # total_data.append(data)
        # except:
        #     pass
    # writeCSV(total_data)


crawling(ad[0])


# if __name__=='__main__':
#     start_time = time.time()
#     pool = Pool(processes=16) # 8개의 프로세스를 사용합니다.
#     pool.map(crawling, get_clothes())  # 2~3초 소요 500개 기준
#     # speed_get_content(get_links()) # 단일 프로세스 12~14초 소요 500여개 기준
#     print("--- %s seconds ---" % (time.time() - start_time))