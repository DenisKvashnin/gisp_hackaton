# Парсер данных юр. лиц по ОГРН

Сервис для получения информации об организации по ОГРН.

## Запуск решения

Можно производить запуск под любыми GNU/Linux ОС, Windows или MacOS.

> Работа решения не проверялась под MacOS
### Linux

#### Создание окружения

```bash
// Если нет модуля venv
sudo apt-get install python3-venv
python3 -m venv env
source env/bin/activate
```

#### Установка модулей в окружение

```bash
pip3 install -r requirements.txt
```

#### Запуск

```bash
export FLASK_ENV=development
flask run
```

### Windows 

#### Создание окружения

```cmd
python -m venv env
env\Scripts\activate
```

#### Установка модулей в окружение

```cmd
pip install -r requirements.txt
```

#### Запуск

```cmd
set FLASK_ENV=development
flask run
```

## Использование

Перейти в браузере по адресу http://127.0.0.1:5000/OGRN/OGRN_NUM

`OGRN_NUM` - ОГРН организации


## Структура выходного файла JSON

Результатом работы будет JSON, имеющий следующую структуру:

```json
{
  "EGRUL_regdate": "20.04.2007", 
  "INN": "772*****60", 
  "KPP": "772*****1", 
  "OGRN": "111*****54088", 
  "OKVED": [
    "33.12", 
    "33.14", 
    "33.20", 
    "46.90", 
    "47.78"
  ], 
  "adress_home": "1", 
  "adress_home_corpus": "3", 
  "adress_post_index": "1*****", 
  "adress_street": "ПРОЕЗД *****", 
  "adress_subject": "ГОРОД МОСКВА", 
  "name_full": "ОБЩЕСТВО С ОГРАНИЧЕННОЙ ОТВЕТСТВЕННОСТЬЮ \"*****\"", 
  "name_short": "ООО \"*****\"", 
  "people_count": "69"
}

```
