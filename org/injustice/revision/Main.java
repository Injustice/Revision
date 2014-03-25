package org.injustice.revision;

import org.injustice.revision.data.History;
import org.injustice.revision.ui.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Azmat on 24/03/2014.
 */
public class Main {
    public List<History> list = new ArrayList<>();
    public List<History> generated = new ArrayList<>();
    private final Random r = new Random();
    private final MainFrame frame = new MainFrame(nextQuestion(), Main.this);
    private int correctInFirstGo = History.values().length;
    public final HashMap<History, Integer> historyDates = new HashMap<>();

    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        for (History h : History.values()) {
            historyDates.put(h, Integer.parseInt(h.answer()));
        }
        SwingUtilities.invokeLater(() ->frame.setVisible(true));
    }

    public void setCorrect(boolean b, int attempts) {
        if (list.size() == historyDates.size()) {
            JOptionPane.showMessageDialog(frame, "You got " + correctInFirstGo + "/" + History.values().length);
            frame.dispose();
            System.out.println(list);
            System.out.println(generated);
            return;
        }
        loop:
        if (b) {
            final History h = nextQuestion();
            if (generated.contains(h) && !list.contains(h)) {
                System.out.println(h);
                frame.setQuestion(h);
            } else {
                break loop;
            }
        } else if (attempts != 1) {
            correctInFirstGo--;
        }
    }

    private History nextQuestion() {
        History[] values = History.values();
        History h = values[r.nextInt(values.length)];
        if (list.contains(h)) {
            return nextQuestion();
        }
        generated.add(h);
        return h;
    }

}
