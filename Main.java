import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

/**
 * Fantasy Character Arena — Java Swing GUI demonstration.
 */
public class Main extends JFrame {
    private final CharacterRoster roster = new CharacterRoster();
    private final DefaultListModel<GameCharacter> listModel = new DefaultListModel<>();
    private final JList<GameCharacter> characterList = new JList<>(listModel);
    private final JTextField nameField = new JTextField(14);
    private final JComboBox<String> typeBox = new JComboBox<>(new String[]{"Warrior", "Mage"});
    private final JTextArea logArea = new JTextArea();
    private final JLabel statusLabel = new JLabel("Create a character to begin.");
    private final JLabel healthLabel = new JLabel("Select a character to view stats.");
    private final Random random = new Random();

    private final Color background = new Color(18, 24, 38);
    private final Color panelColor = new Color(29, 39, 58);
    private final Color accent = new Color(77, 190, 170);
    private final Color textColor = new Color(235, 241, 250);

    public Main() {
        super("Fantasy Character Arena");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 610);
        setMinimumSize(new Dimension(780, 540));
        setLocationRelativeTo(null);
        getContentPane().setBackground(background);
        buildUI();
        addSampleCharacters();
    }

    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(14, 14));
        root.setBackground(background);
        root.setBorder(new EmptyBorder(18, 18, 18, 18));
        setContentPane(root);

        JLabel title = new JLabel("FANTASY CHARACTER ARENA");
        title.setForeground(accent);
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        JLabel subtitle = new JLabel("Build your team • choose abilities • manage your roster");
        subtitle.setForeground(new Color(174, 188, 207));
        JPanel heading = new JPanel(new GridLayout(2, 1, 0, 5));
        heading.setOpaque(false);
        heading.add(title);
        heading.add(subtitle);
        root.add(heading, BorderLayout.NORTH);

        JPanel left = new JPanel(new BorderLayout(8, 8));
        stylePanel(left);
        JLabel rosterTitle = new JLabel("YOUR ROSTER");
        styleLabel(rosterTitle, 15, true);
        left.add(rosterTitle, BorderLayout.NORTH);
        characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        characterList.setBackground(panelColor);
        characterList.setForeground(textColor);
        characterList.setSelectionBackground(accent.darker());
        characterList.setSelectionForeground(Color.WHITE);
        characterList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        left.add(new JScrollPane(characterList), BorderLayout.CENTER);

        JPanel creator = new JPanel(new GridLayout(0, 1, 6, 6));
        creator.setOpaque(false);
        JLabel createLabel = new JLabel("CREATE CHARACTER");
        styleLabel(createLabel, 12, true);
        creator.add(createLabel);
        nameField.setToolTipText("Use 2–20 letters, spaces, hyphens, or apostrophes");
        creator.add(nameField);
        creator.add(typeBox);
        JButton addButton = makeButton("＋ Add Character", accent);
        addButton.addActionListener(e -> createCharacter());
        creator.add(addButton);
        JButton removeButton = makeButton("Remove Selected", new Color(185, 78, 91));
        removeButton.addActionListener(e -> removeSelected());
        creator.add(removeButton);
        left.add(creator, BorderLayout.SOUTH);
        left.setPreferredSize(new Dimension(255, 100));
        root.add(left, BorderLayout.WEST);

        JPanel right = new JPanel(new BorderLayout(10, 10));
        right.setOpaque(false);
        JPanel stats = new JPanel(new GridLayout(0, 1, 5, 5));
        stylePanel(stats);
        JLabel statsTitle = new JLabel("CHARACTER STATUS");
        styleLabel(statsTitle, 15, true);
        stats.add(statsTitle);
        healthLabel.setForeground(textColor);
        healthLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        stats.add(healthLabel);
        statusLabel.setForeground(accent);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
        stats.add(statusLabel);
        right.add(stats, BorderLayout.NORTH);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        actions.setOpaque(false);
        JButton attackButton = makeButton("⚔ Attack", new Color(208, 127, 55));
        attackButton.addActionListener(e -> performAction(false));
        JButton specialButton = makeButton("✦ Special Move", new Color(104, 105, 204));
        specialButton.addActionListener(e -> performAction(true));
        JButton healButton = makeButton("＋ Restore 20 HP", new Color(59, 151, 105));
        healButton.addActionListener(e -> healSelected());
        actions.add(attackButton);
        actions.add(specialButton);
        actions.add(healButton);

        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.setBackground(new Color(12, 17, 28));
        logArea.setForeground(new Color(218, 229, 242));
        logArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        logArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        JScrollPane logScroll = new JScrollPane(logArea);
        logScroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(65, 81, 105)), "BATTLE LOG",
                0, 0, new Font("SansSerif", Font.BOLD, 12), accent));

        JPanel center = new JPanel(new BorderLayout(8, 8));
        center.setOpaque(false);
        center.add(actions, BorderLayout.NORTH);
        center.add(logScroll, BorderLayout.CENTER);
        right.add(center, BorderLayout.CENTER);
        root.add(right, BorderLayout.CENTER);

        characterList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) updateStats();
        });
    }

    private void addSampleCharacters() {
        addCharacter(new Warrior("Arthas"));
        addCharacter(new Mage("Merlin"));
        characterList.setSelectedIndex(0);
        log("Welcome to the Arena! Select a character and choose an action.");
    }

    private void stylePanel(JPanel panel) {
        panel.setBackground(panelColor);
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));
    }

    private void styleLabel(JLabel label, int size, boolean bold) {
        label.setForeground(textColor);
        label.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, size));
    }

    private JButton makeButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBorder(new EmptyBorder(9, 12, 9, 12));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void createCharacter() {
        String name = nameField.getText().trim();
        if (!name.matches("[\\p{L}][\\p{L} '-]{0,19}") || name.length() < 2) {
            JOptionPane.showMessageDialog(this,
                    "Enter a name of 2–20 characters. Start with a letter; letters, spaces, hyphens, and apostrophes are allowed.",
                    "Invalid Name", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocusInWindow();
            return;
        }
        for (GameCharacter character : roster.getAll()) {
            if (character.getName().equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(this, "That character name is already in your roster.",
                        "Duplicate Name", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        GameCharacter character = "Warrior".equals(typeBox.getSelectedItem())
                ? new Warrior(name) : new Mage(name);
        addCharacter(character);
        characterList.setSelectedIndex(listModel.size() - 1);
        nameField.setText("");
        statusLabel.setText("Character added successfully.");
        log("Created " + character.getType() + " " + character.getName() + ".");
    }

    private void addCharacter(GameCharacter character) {
        roster.add(character);
        listModel.addElement(character);
    }

    private void removeSelected() {
        int index = characterList.getSelectedIndex();
        if (index < 0) {
            showSelectMessage();
            return;
        }
        GameCharacter removed = roster.remove(index);
        listModel.remove(index);
        log("Removed " + removed.getName() + " from the roster.");
        if (!listModel.isEmpty()) characterList.setSelectedIndex(Math.min(index, listModel.size() - 1));
        else {
            healthLabel.setText("Select a character to view stats.");
            statusLabel.setText("Roster is empty. Create a character.");
        }
    }

    private void performAction(boolean special) {
        GameCharacter character = characterList.getSelectedValue();
        if (character == null) { showSelectMessage(); return; }
        if (!character.isAlive()) {
            statusLabel.setText(character.getName() + " needs restoring first.");
            log(character.getName() + " is out of energy. Restore HP before acting.");
            return;
        }
        String message = special ? character.specialMove() : character.attack();
        log(message);
        int damage = special ? 20 + random.nextInt(16) : character.getAttackPower();
        statusLabel.setText((special ? character.getAbilityName() : "Attack") + " used!");
        log("Power rating: " + damage + " (demo action; no opponent system yet).");
        updateStats();
    }

    private void healSelected() {
        GameCharacter character = characterList.getSelectedValue();
        if (character == null) { showSelectMessage(); return; }
        if (!character.isAlive()) {
            statusLabel.setText("This character cannot be restored after reaching 0 HP.");
            return;
        }
        int restored = character.heal(20);
        if (restored == 0) log(character.getName() + " already has full HP.");
        else log(character.getName() + " restored " + restored + " HP.");
        statusLabel.setText("Restore action complete.");
        updateStats();
        characterList.repaint();
    }

    private void updateStats() {
        GameCharacter character = characterList.getSelectedValue();
        if (character == null) return;
        healthLabel.setText("<html><b>" + character.getName() + "</b> • " + character.getType()
                + " • HP: " + character.getHealth() + "/" + character.getMaxHealth()
                + " • Attack: " + character.getAttackPower()
                + "<br>Special ability: " + character.getAbilityName() + "</html>");
        characterList.repaint();
    }

    private void showSelectMessage() {
        JOptionPane.showMessageDialog(this, "Please select a character first.",
                "No Character Selected", JOptionPane.INFORMATION_MESSAGE);
    }

    private void log(String message) {
        logArea.append("› " + message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
