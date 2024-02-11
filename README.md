## SEQUENCES PRINTER

### Неблокирующий принтер

Если сильно упростить, то похоже на асинхронное логирование. 

Есть несколько вариантов реализации:
1. Использовать `Timer`
2. Выделять потоки в ручную
3. Использовать `Executors`

Рассмотрим `Timer` в документации пишется:

> Corresponding to each Timer object is a single background thread that is used to execute all of the timer's tasks, sequentially. Timer tasks should complete quickly. If a timer task takes excessive time to complete, it "hogs" the timer's task execution thread.

Могут быть проблемы с принтером, который должен отработать N время.

Ручное выделение потока потребует больше внимания к деталям.

**Итого:** Использование `Executors` дает простоту

### Тесты

Приведены тесты для основных компонентов

### Логгеры принтера

Стандартно используется `ConsoleLogger`. Но также есть возможность применить
`NonTestedFileLogger`, который пишет в файл `./printed.txt`. `NonTestedFileLogger` пока не протестирован, но работает - замените логгер на `new NonTestedFileLogger("./printed.txt")` в `Main` файле,
запустите программу, подождите около минуты и затем остановите в ручную. У вас появится `./printed.txt`. Проверьте содержимое.

### Другие проекты

https://ethanisaac.homes/about