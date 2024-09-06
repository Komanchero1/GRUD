  Замените код в методе init на DI со Spring с использованием методов конфигурирования бинов:


Java Config — ветка feature/di-java.

 Реализуем внедрение зависимостей с использованием Java Config
 
 В методе MainServlet создается экземпляр AnnotationConfigApplicationContext и передается в него класс ApplicationConfig. Это позволяет Spring найти и создать все необходимые объекты, которые мы определили в AppConfig. Затем мы получаем экземпляр PostController и присваивется переменной controller в сервлете.

