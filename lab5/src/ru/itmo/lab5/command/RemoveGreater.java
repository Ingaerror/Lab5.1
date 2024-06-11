package ru.itmo.lab5.command;

import static ru.itmo.lab5.util.ConsoleUtil.readLabWorkFromConsole;

import ru.itmo.lab5.entity.LabWork;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Класс команды 'remove_greater'.
 */
public class RemoveGreater implements Executable {

    private final LinkedHashSet<LabWork> labWorks;

    public RemoveGreater(LinkedHashSet<LabWork> labWorks) {
        this.labWorks = labWorks;
    }

    @Override
    public void execute(String command, Scanner scanner) {
        LabWork labWorkGreater = readLabWorkFromConsole(null, scanner);
        removeGreater(labWorkGreater);
    }

    private void removeGreater(LabWork labWork) {
        List<LabWork> greaterLabWorks = labWorks.stream()
                .filter(existingLabWork -> existingLabWork.compareTo(labWork) > 0)
                .collect(Collectors.toList());
        if (greaterLabWorks.isEmpty()) {
            System.out.println("Элементы, превышающие заданный, не найдены.");
        } else {
            greaterLabWorks.forEach(labWorks::remove);
            System.out.println("Элементы, превышающие заданный, успешно удалены.");
        }
    }
}
