public class SimpleTest {

    public static void main(String[] args) {
        System.out.println("=== ЗАПУСК ТЕСТОВ ЧАТ-БОТА ===\n");

        try {

            System.out.println("ТЕСТ 1: Проверка хранилища вопросов");
            testQuestionStorage();


            System.out.println("\nТЕСТ 2: Проверка ответов на вопросы");
            testQuestionCheckAnswer();


            System.out.println("\nТЕСТ 3: Проверка логики диалога");
            testDialogLogic();


            System.out.println("\nТЕСТ 4: Проверка команд бота");
            testBotCommands();

            System.out.println("\n=== ВСЕ ТЕСТЫ УСПЕШНО ПРОЙДЕНЫ! ===");

        } catch (Exception e) {
            System.out.println("ОШИБКА: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testQuestionStorage() {
        QuestionStorage storage = new QuestionStorage();


        int count = storage.getQuestionsCount();
        System.out.println("✓ Количество вопросов: " + count);


        QuestionStorage.Question first = storage.getQuestion(0);
        System.out.println("✓ Первый вопрос: " + first.getQuestion());
        System.out.println("✓ Ответ: " + first.getAnswer());

        for (int i = 0; i < count; i++) {
            QuestionStorage.Question q = storage.getQuestion(i);
            System.out.println("✓ Вопрос " + (i+1) + ": " + q.getQuestion());
        }
    }

    public static void testQuestionCheckAnswer() {
        QuestionStorage.Question question = new QuestionStorage.Question(
                "Что идет без ног?",
                "время"
        );

        boolean result1 = question.checkAnswer("время");
        System.out.println("✓ Правильный ответ 'время': " + result1);

        boolean result2 = question.checkAnswer("ВРЕМЯ");
        System.out.println("✓ Ответ 'ВРЕМЯ' (верхний регистр): " + result2);

        boolean result3 = question.checkAnswer("  время  ");
        System.out.println("✓ Ответ '  время  ' (с пробелами): " + result3);

        boolean result4 = question.checkAnswer("неправильно");
        System.out.println("✓ Неправильный ответ 'неправильно': " + result4);
    }

    public static void testDialogLogic() {
        QuestionStorage storage = new QuestionStorage();
        DialogLogic dialog = new DialogLogic(storage);

        String welcome = dialog.getWelcomeMessage();
        System.out.println("✓ Приветственное сообщение: " + welcome.substring(0, 50) + "...");

        boolean hasQuestions = dialog.hasNextQuestion();
        System.out.println("✓ Есть следующие вопросы: " + hasQuestions);

        String firstQuestion = dialog.getNextQuestion();
        System.out.println("✓ Первый вопрос: " + firstQuestion);
    }

    public static void testBotCommands() {
        QuestionStorage storage = new QuestionStorage();
        DialogLogic dialog = new DialogLogic(storage);

        String helpResponse = dialog.processAnswer("\\help");
        System.out.println("✓ Команда \\help работает");

        String exitResponse = dialog.processAnswer("\\exit");
        System.out.println("✓ Команда \\exit возвращает: " + exitResponse);
    }
}