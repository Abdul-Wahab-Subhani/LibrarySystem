package LibrarySystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class LibraryApp {

    private JFrame frame;
    private JTextField inputField;
    private JButton confirmButton;

    private JPanel boxTotal, boxAvailable, boxBorrowed;
    private JScrollPane scrollPane;
    private JEditorPane outputPane;

    private Library library;
    private String currentAction = "";
    private String tempTitle = "";

    // Theme
    private final Color NAV_COLOR = new Color(45, 62, 80);
    private final Color BG_COLOR = new Color(236, 240, 241);
    private final Color ACCENT_COLOR = new Color(52, 152, 219);
    private final Font FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private final Font DASHBOARD_FONT = new Font("Segoe UI", Font.BOLD, 16);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryApp().frame.setVisible(true));
    }

    public LibraryApp() {
        library = new Library();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Library Management System");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel navPanel = createNavigationPanel();
        JPanel mainPanel = createMainPanel();

        frame.getContentPane().add(navPanel);
        frame.getContentPane().add(mainPanel);
    }

    private JPanel createNavigationPanel() {
        JPanel navPanel = new JPanel();
        navPanel.setBounds(0, 0, 238, 563);
        navPanel.setBackground(NAV_COLOR);
        navPanel.setLayout(new GridLayout(0, 1, 10, 10));
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        String[] buttons = {
            "Dashboard", "Add Book", "List Available Books", "Borrow Book",
            "Borrowed Book List", "Return Book", "Search Book", "Exit"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setBackground(ACCENT_COLOR);
            btn.setForeground(Color.WHITE);
            btn.setFont(FONT);
            btn.setFocusPainted(false);
            navPanel.add(btn);
            setupNavAction(btn);
        }

        return navPanel;
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(248, 0, 738, 563);
        mainPanel.setBackground(BG_COLOR);
        mainPanel.setLayout(null);

        JPanel inputPanel = new JPanel(null);
        inputPanel.setBounds(0, 0, 738, 50);
        inputPanel.setBackground(BG_COLOR);

        inputField = new JTextField();
        inputField.setBounds(48, 10, 423, 30);
        inputField.setFont(FONT);

        confirmButton = new JButton("Enter");
        confirmButton.setBounds(481, 10, 112, 30);
        confirmButton.setBackground(ACCENT_COLOR);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(FONT);

        inputField.addActionListener(e -> handleConfirm());
        confirmButton.addActionListener(e -> handleConfirm());

        inputPanel.add(inputField);
        inputPanel.add(confirmButton);
        mainPanel.add(inputPanel);

        outputPane = new JEditorPane("text/html", "");
        outputPane.setEditable(false);
        outputPane.setFont(FONT);
        outputPane.setBackground(Color.WHITE);

        scrollPane = new JScrollPane(outputPane);
        scrollPane.setBounds(47, 60, 655, 445);
        mainPanel.add(scrollPane);

        JPanel dashboard = new JPanel(new GridLayout(1, 3, 10, 10));
        dashboard.setBounds(35, 515, 677, 46);
        dashboard.setBackground(BG_COLOR);
        dashboard.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        boxTotal = createDashboardBox("Total Books: 0");
        boxAvailable = createDashboardBox("Available Books: 0");
        boxBorrowed = createDashboardBox("Borrowed Books: 0");

        dashboard.add(boxTotal);
        dashboard.add(boxAvailable);
        dashboard.add(boxBorrowed);
        mainPanel.add(dashboard);

        return mainPanel;
    }

    private JPanel createDashboardBox(String text) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(ACCENT_COLOR, 2));
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(DASHBOARD_FONT);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private void updateDashboard() {
        updateDashboardBox(boxTotal, "Total Books: " + library.getAllBooks().size());
        updateDashboardBox(boxAvailable, "Available Books: " + library.getAvailableBooks().size());
        updateDashboardBox(boxBorrowed, "Borrowed Books: " + library.getBorrowedBooks().size());
    }

    private void updateDashboardBox(JPanel box, String text) {
        JLabel label = (JLabel) box.getComponent(0);
        label.setText(text);
    }

    private void setupNavAction(JButton button) {
        button.addActionListener(e -> {
            String action = button.getText();
            inputField.setVisible(true);
            confirmButton.setVisible(true);
            inputField.setText("");

            switch (action) {
                case "Dashboard" -> {
                    inputField.setVisible(false);
                    confirmButton.setVisible(false);
                    currentAction = "";
                    updateDashboard();
                    scrollPane.setViewportView(outputPane); // ✅ Reset table view
                    outputPane.setText("""
                    <html>
                     <body style='font-family:Segoe UI; font-size:12pt; padding:10px'>
                        <h2>📚 Library Management System</h2>
                        <p>This Java Swing application simulates a basic library where users can:</p>
                        <ul>
                            <li>Add new books</li>
                            <li>List available/borrowed books</li>
                            <li>Borrow and return books</li>
                            <li>Search for books by title</li>
                        </ul>
                        <p>More features like genres, user accounts, and analytics can be added in future.</p>
                     </body>
                    </html>""");
                }
                case "Add Book" -> {
                    scrollPane.setViewportView(outputPane); // ✅ Reset table view
                    outputPane.setText("<html><body>Enter book title:</body></html>");
                    currentAction = "addBookTitle";
                }
                case "List Available Books" -> {
                    inputField.setVisible(false);
                    confirmButton.setVisible(false);
                    displayBooks(library.getAvailableBooks());
                    currentAction = "";
                }
                case "Borrow Book" -> {
                    displayBooks(library.getAvailableBooks());
                    currentAction = "borrowBook";
                }
                case "Borrowed Book List" -> {
                    inputField.setVisible(false);
                    confirmButton.setVisible(false);
                    displayBooks(library.getBorrowedBooks());
                    currentAction = "";
                }
                case "Return Book" -> {
                    if (library.getBorrowedBooks().isEmpty()) {
                        scrollPane.setViewportView(outputPane); // ✅ Reset table view
                        outputPane.setText("<html><body>No books are currently borrowed.</body></html>");
                        inputField.setVisible(false);
                        confirmButton.setVisible(false);
                        currentAction = "";
                    } else {
                        displayBooks(library.getBorrowedBooks());
                        currentAction = "returnBook";
                    }
                }
                case "Search Book" -> {
                    scrollPane.setViewportView(outputPane); // ✅ Reset table view
                    outputPane.setText("<html><body>Enter book title to search:</body></html>");
                    currentAction = "searchBook";
                }
                case "Exit" -> {
                    int confirm = JOptionPane.showConfirmDialog(frame, "Exit the application?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) System.exit(0);
                }
            }
        });
    }

    private void displayBooks(ArrayList<Book> books) {
        String[] columns = {"Title", "Author", "Status"};
        String[][] data = new String[books.size()][3];
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            data[i][0] = b.getTitle();
            data[i][1] = b.getAuthor();
            data[i][2] = b.isBorrowed() ? "Borrowed" : "Available";
        }

        JTable table = new JTable(data, columns);
        table.setFont(FONT);
        table.setRowHeight(25);
        table.setEnabled(false);
        table.getTableHeader().setFont(DASHBOARD_FONT);
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        scrollPane.setViewportView(table);
    }

    private void handleConfirm() {
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            scrollPane.setViewportView(outputPane); // ✅ Reset table view
            outputPane.setText("<html><body>Input cannot be empty.</body></html>");
            return;
        }

        scrollPane.setViewportView(outputPane); // ✅ Always reset to show messages

        switch (currentAction) {
            case "addBookTitle" -> {
                tempTitle = input;
                outputPane.setText("<html><body>Enter author name:</body></html>");
                currentAction = "addBookAuthor";
                inputField.setText("");
            }
            case "addBookAuthor" -> {
                Book book = new Book(tempTitle, input);
                library.addBookAtStart(book);
                updateDashboard();
                outputPane.setText("<html><body>✅ Book added:<br><b>" + book + "</b></body></html>");
                currentAction = "";
                inputField.setText("");
            }
            case "borrowBook" -> {
                boolean borrowed = library.borrowBook(input);
                updateDashboard();
                outputPane.setText("<html><body>" + (borrowed ? "✅ Book borrowed." : "❌ Book not available.") + "</body></html>");
                currentAction = "";
                inputField.setText("");
            }
            case "returnBook" -> {
                boolean returned = library.returnBook(input);
                updateDashboard();
                outputPane.setText("<html><body>" + (returned ? "✅ Book returned." : "❌ Not found or not borrowed.") + "</body></html>");
                currentAction = "";
                inputField.setText("");
            }
            case "searchBook" -> {
                Book found = library.searchBook(input);
                if (found != null) {
                    outputPane.setText("<html><body><b>✅ Found:</b><br>" + found + "</body></html>");
                } else {
                    outputPane.setText("<html><body>❌ Book not found.</body></html>");
                }
                currentAction = "";
                inputField.setText("");
            }
            default -> outputPane.setText("<html><body>Select an action first.</body></html>");
        }
    }
}
