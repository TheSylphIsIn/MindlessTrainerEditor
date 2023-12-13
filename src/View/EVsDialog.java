package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.Arrays;

public class EVsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSpinner hpSpinner;
    private JSpinner spdSpinner;
    private JSpinner atkSpinner;
    private JSpinner defSpinner;
    private JSpinner spatkSpinner;
    private JSpinner spdefSpinner;
    private JLabel totalLabel;
    private JButton setAllButton;
    private JSpinner spinner1;
    private int[] oldEvs;
    private int[] newEvs;
    private int[] returning;
    private Boolean notIvs;

    public EVsDialog(Boolean notIvs, int[] evs) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.notIvs = notIvs;
        if (notIvs) {
            setTitle("Editing EVs");
            hpSpinner.setModel(new SpinnerNumberModel(0, 0, 252, 4));
            atkSpinner.setModel(new SpinnerNumberModel(0, 0, 252, 4));
            defSpinner.setModel(new SpinnerNumberModel(0, 0, 252, 4));
            spdSpinner.setModel(new SpinnerNumberModel(0, 0, 252, 4));
            spatkSpinner.setModel(new SpinnerNumberModel(0, 0, 252, 4));
            spdefSpinner.setModel(new SpinnerNumberModel(0, 0, 252, 4));
            totalLabel.setText("Total: " + Arrays.stream(evs).sum() + "/508");
        }
        else
        {
            hpSpinner.setModel(new SpinnerNumberModel(0, 0, 31, 1));
            atkSpinner.setModel(new SpinnerNumberModel(0, 0, 31, 1));
            defSpinner.setModel(new SpinnerNumberModel(0, 0, 31, 1));
            spdSpinner.setModel(new SpinnerNumberModel(0, 0, 31, 1));
            spatkSpinner.setModel(new SpinnerNumberModel(0, 0, 31, 1));
            spdefSpinner.setModel(new SpinnerNumberModel(0, 0, 31, 1));
            totalLabel.setText("Total: " + Arrays.stream(evs).sum() + "/186, Avg: " +  new BigDecimal(Arrays.stream(evs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());

        }

        oldEvs = evs;

        hpSpinner.setValue(evs[0]);
        atkSpinner.setValue(evs[1]);
        defSpinner.setValue(evs[2]);
        spdSpinner.setValue(evs[3]);
        spatkSpinner.setValue(evs[4]);
        spdefSpinner.setValue(evs[5]);

        newEvs = new int[]{(int) hpSpinner.getValue(), (int) atkSpinner.getValue(), (int) defSpinner.getValue(),
                (int) spdSpinner.getValue(), (int) spatkSpinner.getValue(), (int) spdefSpinner.getValue()};

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int val = (int) spinner1.getValue();
                hpSpinner.setValue(val);
                atkSpinner.setValue(val);
                defSpinner.setValue(val);
                spdSpinner.setValue(val);
                spatkSpinner.setValue(val);
                spdefSpinner.setValue(val);
                if (notIvs)
                    totalLabel.setText("Total: " + val * 6 + "/508");
                else
                    totalLabel.setText("Total: " + (val * 6) + "/186, Avg: " +  val);

                newEvs[0] = val;
                newEvs[1] = val;
                newEvs[2] = val;
                newEvs[3] = val;
                newEvs[4] = val;
                newEvs[5] = val;
            }
        });

        JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) hpSpinner.getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    hpSpinner.commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                newEvs[0] = (int) hpSpinner.getValue();
                int sum = Arrays.stream(newEvs).sum();
                if (notIvs)
                    totalLabel.setText("Total: " + sum + "/508");
                else
                    totalLabel.setText("Total: " + Arrays.stream(newEvs).sum() + "/186, Avg: " +  new BigDecimal(Arrays.stream(newEvs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                if (sum > 508)
                    totalLabel.setForeground(Color.red);
                else
                    totalLabel.setForeground(Color.black);
            }
        });

        editor = (JSpinner.DefaultEditor) atkSpinner.getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    atkSpinner.commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                newEvs[1] = (int) atkSpinner.getValue();
                int sum = Arrays.stream(newEvs).sum();
                if (notIvs)
                    totalLabel.setText("Total: " + sum + "/508");
                else
                    totalLabel.setText("Total: " + Arrays.stream(newEvs).sum() + "/186, Avg: " +  new BigDecimal(Arrays.stream(newEvs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                if (sum > 508)
                    totalLabel.setForeground(Color.red);
                else
                    totalLabel.setForeground(Color.black);
            }
        });

        editor = (JSpinner.DefaultEditor) defSpinner.getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    defSpinner.commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                newEvs[2] = (int) defSpinner.getValue();
                int sum = Arrays.stream(newEvs).sum();
                if (notIvs)
                    totalLabel.setText("Total: " + sum + "/508");
                else
                    totalLabel.setText("Total: " + Arrays.stream(newEvs).sum() + "/186, Avg: " +  new BigDecimal(Arrays.stream(newEvs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                if (sum > 508)
                    totalLabel.setForeground(Color.red);
                else
                    totalLabel.setForeground(Color.black);
            }
        });

        editor = (JSpinner.DefaultEditor) spdSpinner.getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    spdSpinner.commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                newEvs[3] = (int) spdSpinner.getValue();
                int sum = Arrays.stream(newEvs).sum();
                if (notIvs)
                    totalLabel.setText("Total: " + sum + "/508");
                else
                    totalLabel.setText("Total: " + Arrays.stream(newEvs).sum() + "/186, Avg: " +  new BigDecimal(Arrays.stream(newEvs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                if (sum > 508)
                    totalLabel.setForeground(Color.red);
                else
                    totalLabel.setForeground(Color.black);
            }
        });

        editor = (JSpinner.DefaultEditor) spatkSpinner.getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    spatkSpinner.commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                newEvs[4] = (int) spatkSpinner.getValue();
                int sum = Arrays.stream(newEvs).sum();
                if (notIvs)
                    totalLabel.setText("Total: " + sum + "/508");
                else
                    totalLabel.setText("Total: " + Arrays.stream(newEvs).sum() + "/186, Avg: " +  new BigDecimal(Arrays.stream(newEvs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                if (sum > 508)
                    totalLabel.setForeground(Color.red);
                else
                    totalLabel.setForeground(Color.black);
            }
        });

        editor = (JSpinner.DefaultEditor) spdefSpinner.getEditor();

        editor.getTextField().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    spdefSpinner.commitEdit();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                newEvs[5] = (int) spdefSpinner.getValue();
                int sum = Arrays.stream(newEvs).sum();
                if (notIvs)
                    totalLabel.setText("Total: " + sum + "/508");
                else
                    totalLabel.setText("Total: " + Arrays.stream(newEvs).sum() + "/186, Avg: " +  new BigDecimal(Arrays.stream(newEvs).average().getAsDouble()).setScale(2, RoundingMode.HALF_UP).doubleValue());
                if (sum > 508)
                    totalLabel.setForeground(Color.red);
                else
                    totalLabel.setForeground(Color.black);
            }
        });
    }

    private void onOK() {
        // add your code here
        returning = newEvs;
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        returning = oldEvs;
        dispose();
    }

    public int[] showAndWait()
    {
        setVisible(true);
        return returning;
    }
}
