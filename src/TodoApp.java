import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TodoApp {
    private static DefaultListModel<Task> model;
    private static JList<Task> list;

    public static void main(String[] args) {
        // configuracione de la apliaccion(ventana)
        JFrame frame = new JFrame("Todo App Premium");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(30, 30, 30));

        // Listado de tareas
        JLabel title = new JLabel("Mis Tareas");
        title.setFont(new Font("SansSerif", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        mainPanel.add(title, BorderLayout.NORTH);

        model = new DefaultListModel<>();
        list = new JList<>(model);
        list.setBackground(new Color(45, 45, 45));
        list.setForeground(Color.WHITE);
        list.setFixedCellHeight(50);

        JScrollPane scrollPane = new JScrollPane(list);
        mainPanel.add(scrollPane, BorderLayout.CENTER);


        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(new Color(30, 30, 30));
        // formulario label y inputs
        controlPanel.add(createLabel("Título:"));
        JTextField taskTitle = createTextField();
        controlPanel.add(taskTitle);
        controlPanel.add(Box.createVerticalStrut(10));


        controlPanel.add(createLabel("Descripción:"));
        JTextField taskDesc = createTextField();
        controlPanel.add(taskDesc);
        controlPanel.add(Box.createVerticalStrut(10));

        controlPanel.add(createLabel("Estado:"));
        String[] states = {"Pendiente", "En Proceso", "Completada"};
        JComboBox<String> statusCombo = new JComboBox<>(states);
        statusCombo.setBackground(new Color(45, 45, 45));
        statusCombo.setForeground(Color.WHITE);
        controlPanel.add(statusCombo);
        controlPanel.add(Box.createVerticalStrut(20));

        // acciones
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        buttonPanel.setBackground(new Color(30, 30, 30));
        JButton btnAdd = createStyledButton("Añadir", new Color(40, 167, 69));
        JButton btnUpdate = createStyledButton("Editar", new Color(0, 123, 255));
        JButton btnDelete = createStyledButton("Borrar", new Color(220, 53, 69));

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        controlPanel.add(buttonPanel);

        mainPanel.add(controlPanel, BorderLayout.SOUTH);


        btnAdd.addActionListener(e -> {
            String titleText = taskTitle.getText().trim();
            if (titleText.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Error: El título es obligatorio.", "Validación", JOptionPane.ERROR_MESSAGE);
                return;
            }

            model.addElement(new Task(
                    titleText,
                    taskDesc.getText().trim(),
                    statusCombo.getSelectedItem().toString()
            ));

            // Limpiar campos
            taskTitle.setText("");
            taskDesc.setText("");
            statusCombo.setSelectedIndex(0);
        });

// Editar Estado de Tarea Seleccionada
        btnUpdate.addActionListener(e -> {
            int idx = list.getSelectedIndex();
            if (idx == -1) {
                JOptionPane.showMessageDialog(frame, "Por favor, seleccione una tarea de la lista para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Obtener la tarea actual
            Task selectedTask = model.getElementAt(idx);

            // Mostrar diálogo para elegir nuevo estado
            String[] options = {"Pendiente", "En Proceso", "Completada"};
            String newStatus = (String) JOptionPane.showInputDialog(
                    frame,
                    "Cambiar estado de: " + selectedTask.title,
                    "Editar Estado",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    selectedTask.status
            );

            if (newStatus != null) {
                selectedTask.status = newStatus;
                model.set(idx, selectedTask);
            }
        });

        btnDelete.addActionListener(e -> {
            int idx = list.getSelectedIndex();
            if (idx != -1) {
                model.remove(idx);
            } else {
                JOptionPane.showMessageDialog(frame, "Seleccione una tarea para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private static JLabel createLabel(String txt) {
        JLabel l = new JLabel(txt);
        l.setForeground(new Color(180, 180, 180));
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        return l;
    }

    private static JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        tf.setBackground(new Color(45, 45, 45));
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 80)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        return tf;
    }

    private static JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}