FROM tiangolo/uwsgi-nginx:python3.8
ENV STATIC_URL /static
ENV STATIC_PATH /var/www/app/static
COPY ./requirements.txt /var/www/requirements.txt
# RUN  apt-get update && apt-get install -y bash
RUN python -m pip install --upgrade pip
RUN pip install -r /var/www/requirements.txt
COPY . /app
WORKDIR /app
EXPOSE 8088
# CMD [ "python", "main.py" ]
CMD gunicorn --worker-class gevent --workers 1 --bind 0.0.0.0:8088 -t 300 wsgi:app
