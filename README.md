## SEQUENCES PRINTER

### Неблокирующий принтер

2 рабочих потока, 1 поток выделенный для печати

### Тесты

Приведены тесты для основных компонентов

### Логгеры принтера

Стандартно используется `ConsoleLogger`. Но также есть возможность применить
`NonTestedFileLogger`, который пишет в файл `./printed.txt`. `NonTestedFileLogger` пока не протестирован, но работает - замените логгер в `Main` файле, запустите программу, подождите около минуты и затем остановите в ручную. У вас появится `./printed.txt`. Проверьте содержимое.

### Другие проекты

https://ethanisaac.homes/about