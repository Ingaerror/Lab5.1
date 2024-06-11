package ru.itmo.lab5.util;

import ru.itmo.lab5.command.Executable;

import java.util.Deque;
import java.util.Map;
import java.util.Scanner;

/**
 * Вспомогательный класс для выполнения команд.
 */
public class CommandExecutor {

    private final Map<String, Executable> commands;
    private final Deque<String> commandHistory;

    public CommandExecutor(Map<String, Executable> commands, Deque<String> commandHistory) {
        this.commands = commands;
        this.commandHistory = commandHistory;
    }

    public void executeCommand(String command, Scanner scanner) {
        addCommandToHistory(command);
        final String commandName = splitCommand(command)[0];
        if (commands.containsKey(commandName)) {
            commands.get(commandName).execute(command, scanner);
        } else {
            System.out.println("Неизвестная команда. Введите 'help' для получения списка доступных команд.");
        }
    }

    private void addCommandToHistory(String command) {
        if (commandHistory.size() == 11) {
            commandHistory.removeFirst();
        }
        commandHistory.add(command);
    }

    private String[] splitCommand(String command) {
        int i = command.trim().lastIndexOf(' ');
        if (i < 0) {
            return new String[] {command.trim()};
        }
        return new String[] {command.substring(0, i).trim(), command.substring(i).trim()};
    }
}
