import os
import datetime

today = datetime.datetime.today()
week_number = today.isocalendar()[1]
year = str(today.isocalendar()[0])[-2:]
release_tag = f"{year}w{week_number}"

with open(os.environ['GITHUB_OUTPUT'], 'a')as fh:
    print(f'date={release_tag}', file=fh)
