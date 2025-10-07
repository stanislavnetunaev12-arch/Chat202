public class Main {
    public static void main(String[] args) {

        QuestionStorage questionStorage = new QuestionStorage();
        DialogLogic dialogLogic = new DialogLogic(questionStorage);
        ConsoleInterface console = new ConsoleInterface();


        runChatBot(dialogLogic, console);
    }

    public static void runChatBot(DialogLogic dialogLogic, ConsoleInterface console) {
        console.displayMessage(dialogLogic.getWelcomeMessage());

        while (true) {
            if (dialogLogic.hasNextQuestion()) {
                String question = dialogLogic.getNextQuestion();
                console.displayMessage("\nЗагадка: " + question);
            }

            String userAnswer = console.getUserInput();
            String response = dialogLogic.processAnswer(userAnswer);

            if (response.equals("exit")) {
                console.displayMessage("До свидания! Возвращайся еще!");
                break;
            }

            console.displayMessage(response);
        }

        console.close();
    }
}