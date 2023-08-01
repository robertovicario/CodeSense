package src.app.layout;

import src.core.CoreImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * The App class represents the application GUI.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class App extends JFrame {
    private final JComboBox<String> jComboBox1 = new JComboBox<>();
    private final JButton jButton1 = new JButton("Run");
    private final JButton jButton2 = new JButton("Optimize");
    private final JButton jButton3 = new JButton("Clear");
    private final JButton jButton4 = new JButton("Export");
    private final JButton jButton5 = new JButton("Compare");
    private final JButton jButton6 = new JButton("Clear");
    private final JTextArea jTextArea1 = new JTextArea();
    private final JTable jTable1 = new JTable();

    /**
     * Constructs a new instance of the App class.
     */
    public App() {
        initComponents();
        initEvents();
    }

    /**
     * Initializes the components of the application GUI.
     */
    public void initComponents() {
        JPanel jPanel1 = new JPanel();
        JLabel jLabel1 = new JLabel("Language:");
        JSeparator jSeparator1 = new JSeparator();
        JToolBar jToolBar1 = new JToolBar();
        Box.Filler filler1 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));
        JToolBar jToolBar2 = new JToolBar();
        Box.Filler filler2 = new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(32767, 0));

        JScrollPane jScrollPane1 = new JScrollPane();
        JScrollPane jScrollPane2 = new JScrollPane();

        jComboBox1.setModel(new DefaultComboBoxModel<>(new String[] {"-", "C", "Java", "Python"}));

        jToolBar1.setRollover(true);

        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);
        jToolBar1.add(filler1);

        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jToolBar2.setRollover(true);

        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(jButton4);

        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(jButton5);
        jToolBar2.add(filler2);

        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(SwingConstants.BOTTOM);
        jToolBar2.add(jButton6);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", Font.PLAIN, 13));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "ID", "Language", "Time", "Space"
                }
        ) {
            final boolean[] canEdit = new boolean [] {
                    false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jSeparator1)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboBox1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(jToolBar1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jToolBar2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jComboBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jToolBar1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jToolBar2, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        setTitle("CodeSense");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(700, 500));
        setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Initializes the event listeners for the buttons in the GUI.
     */
    public void initEvents() {
        CoreImpl core = new CoreImpl((DefaultComboBoxModel<String>) jComboBox1.getModel(), jTextArea1, (DefaultTableModel) jTable1.getModel());

        // run
        jButton1.addActionListener(e -> core.run());

        // optimize
        jButton2.addActionListener(e -> core.optimize());

        // clear
        jButton3.addActionListener(e -> core.clearTextArea());

        // export
        jButton4.addActionListener(e -> core.export());

        // compare
        jButton5.addActionListener(e -> core.compare());

        // clear
        jButton6.addActionListener(e -> core.clearTable());
    }
}
