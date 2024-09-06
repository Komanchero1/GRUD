   Изменение кода путем внедрениея зависимостей (Dependency Injection) с использованием Spring и аннотационной конфигурации.

   - Создание конфигурационного классф, который будет содержать определение бинов.
   - В конструкторе MainServlet создается экземпляр ApplicationContext с использованием AnnotationConfigApplicationContext и передается в него класс AppConfig. Затем получается  экземпляр PostController из контекста и сохраняется  в поле controller.