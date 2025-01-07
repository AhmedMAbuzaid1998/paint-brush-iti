package Paint;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class EssPanel extends JPanel {
    private List<Shape> shapes;
    private Shape currentShape;
    private JButton redButton, greenButton, blueButton, rectangleButton, ovalButton, lineButton, freehandButton, eraserButton, clearAllButton;
    private JCheckBox dottedCheckBox, filledCheckBox;
    private JLabel colorLabel, shapeLabel, borderLabel;
    private boolean isDrawingRect, isDrawingOval, isDrawingLine, isFreehandDrawing, isErasing, isDottedLine;
    private List<Point> freehandPoints;
    private Color drawColor;
    private String currentShapeType = "None";
    private String currentBorderType = "Solid";

    private JButton createColorButton(Color color, String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(getBackground());
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());
            }
        };

        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(50, 30));
        return button;
    }

    public EssPanel() {
        this.setFocusable(true);
        this.setBackground(Color.white);
        this.shapes = new ArrayList<>();
        this.freehandPoints = new ArrayList<>();
        this.isDrawingRect = false;
        this.isDrawingOval = false;
        this.isDrawingLine = false;
        this.isFreehandDrawing = false;
        this.isErasing = false;
        this.isDottedLine = false;
        Dimension componentSize = new Dimension(90, 30);

        this.colorLabel = new JLabel("Color: None");
        this.shapeLabel = new JLabel("Shape: None");
        this.borderLabel = new JLabel("Border: Solid");

        this.redButton = createColorButton(Color.RED, "Red");
        setUniformSize(this.redButton, componentSize);
        this.redButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.setDrawColor(Color.RED);
                EssPanel.this.colorLabel.setText("Color: Red");
            }
        });

        this.greenButton = createColorButton(Color.GREEN, "Green");
        setUniformSize(this.greenButton, componentSize);
        this.greenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.setDrawColor(Color.GREEN);
                EssPanel.this.colorLabel.setText("Color: Green");
            }
        });

        this.blueButton = createColorButton(Color.BLUE, "Blue");
        setUniformSize(this.blueButton, componentSize);
        this.blueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.setDrawColor(Color.BLUE);
                EssPanel.this.colorLabel.setText("Color: Blue");
            }
        });

        this.rectangleButton = new JButton("Rectangle");
        this.rectangleButton.setBackground(Color.GRAY);
        this.rectangleButton.setForeground(Color.WHITE);
        setUniformSize(this.rectangleButton, componentSize);
        this.rectangleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.isDrawingRect = !EssPanel.this.isDrawingRect;
                EssPanel.this.isDrawingOval = false;
                EssPanel.this.isDrawingLine = false;
                EssPanel.this.isFreehandDrawing = false;
                EssPanel.this.isErasing = false;
                EssPanel.this.currentShapeType = EssPanel.this.isDrawingRect ? "Rectangle" : "None";
                EssPanel.this.shapeLabel.setText("Shape: " + EssPanel.this.currentShapeType);
            }
        });

        this.ovalButton = new JButton("Oval");
        this.ovalButton.setBackground(Color.GRAY);
        this.ovalButton.setForeground(Color.WHITE);
        setUniformSize(this.ovalButton, componentSize);
        this.ovalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.isDrawingOval = !EssPanel.this.isDrawingOval;
                EssPanel.this.isDrawingRect = false;
                EssPanel.this.isDrawingLine = false;
                EssPanel.this.isFreehandDrawing = false;
                EssPanel.this.isErasing = false;
                EssPanel.this.currentShapeType = EssPanel.this.isDrawingOval ? "Oval" : "None";
                EssPanel.this.shapeLabel.setText("Shape: " + EssPanel.this.currentShapeType);
            }
        });

        this.lineButton = new JButton("Line");
        this.lineButton.setBackground(Color.GRAY);
        this.lineButton.setForeground(Color.WHITE);
        setUniformSize(this.lineButton, componentSize);
        this.lineButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.isDrawingLine = !EssPanel.this.isDrawingLine;
                EssPanel.this.isDrawingRect = false;
                EssPanel.this.isDrawingOval = false;
                EssPanel.this.isFreehandDrawing = false;
                EssPanel.this.isErasing = false;
                EssPanel.this.currentShapeType = EssPanel.this.isDrawingLine ? "Line" : "None";
                EssPanel.this.shapeLabel.setText("Shape: " + EssPanel.this.currentShapeType);
            }
        });

        this.freehandButton = new JButton("Freehand");
        this.freehandButton.setBackground(Color.GRAY);
        this.freehandButton.setForeground(Color.WHITE);
        setUniformSize(this.freehandButton, componentSize);
        this.freehandButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.isFreehandDrawing = !EssPanel.this.isFreehandDrawing;
                EssPanel.this.isDrawingRect = false;
                EssPanel.this.isDrawingOval = false;
                EssPanel.this.isDrawingLine = false;
                EssPanel.this.isErasing = false;
                EssPanel.this.clearFreehandPoints();
                EssPanel.this.currentShapeType = EssPanel.this.isFreehandDrawing ? "Freehand" : "None";
                EssPanel.this.shapeLabel.setText("Shape: " + EssPanel.this.currentShapeType);
            }
        });

        this.eraserButton = new JButton("Eraser");
        this.eraserButton.setBackground(Color.GRAY);
        this.eraserButton.setForeground(Color.WHITE);
        setUniformSize(this.eraserButton, componentSize);
        this.eraserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.isErasing = !EssPanel.this.isErasing;
                EssPanel.this.isDrawingRect = false;
                EssPanel.this.isDrawingOval = false;
                EssPanel.this.isDrawingLine = false;
                EssPanel.this.isFreehandDrawing = false;

            }
        });

        this.clearAllButton = new JButton("Clear All");
        this.clearAllButton.setBackground(Color.GRAY);
        this.clearAllButton.setForeground(Color.WHITE);
        setUniformSize(this.clearAllButton, componentSize);
        this.clearAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.clearDrawings();
            }
        });

        this.dottedCheckBox = new JCheckBox("Dotted ");
        setUniformSize(this.dottedCheckBox, componentSize);
        this.dottedCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EssPanel.this.isDottedLine = EssPanel.this.dottedCheckBox.isSelected();
                EssPanel.this.currentBorderType = EssPanel.this.isDottedLine ? "Dotted" : "Solid";
                EssPanel.this.borderLabel.setText("Border: " + EssPanel.this.currentBorderType);
            }
        });

        this.filledCheckBox = new JCheckBox("Filled");
        setUniformSize(this.filledCheckBox, componentSize);
        this.filledCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (EssPanel.this.filledCheckBox.isSelected()) {
                    EssPanel.this.currentShapeType = "Filled";
                } else {
                    EssPanel.this.currentShapeType = "Unfilled";
                }
                EssPanel.this.borderLabel.setText("Shape: " + EssPanel.this.currentShapeType);
                EssPanel.this.repaint();
            }
        });

        JPanel statusPanel = new JPanel();
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new BoxLayout(toolbar, BoxLayout.Y_AXIS));
        toolbar.setBorder(BorderFactory.createTitledBorder("Tools"));
        this.setLayout(new BorderLayout());
        this.add(toolbar, BorderLayout.WEST);

        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        toolbar.add(colorPanel);
        colorPanel.add(this.redButton);
        colorPanel.add(this.greenButton);
        colorPanel.add(this.blueButton);

        JPanel shapesPanel = new JPanel();
        shapesPanel.setLayout(new BoxLayout(shapesPanel, BoxLayout.Y_AXIS));
        shapesPanel.setBorder(BorderFactory.createTitledBorder("Shapes"));
        toolbar.add(shapesPanel);
        shapesPanel.add(this.rectangleButton);
        shapesPanel.add(this.ovalButton);
        shapesPanel.add(this.lineButton);
        shapesPanel.add(this.freehandButton);

        JPanel borderPanel = new JPanel();
        borderPanel.setLayout(new BoxLayout(borderPanel, BoxLayout.Y_AXIS));
        borderPanel.setBorder(BorderFactory.createTitledBorder("Style"));
        toolbar.add(borderPanel);
        borderPanel.add(this.dottedCheckBox);
        borderPanel.add(this.filledCheckBox);

        toolbar.add(this.eraserButton);
        toolbar.add(this.clearAllButton);


        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        statusPanel.add(colorLabel);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(shapeLabel);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(borderLabel);
        this.add(statusPanel, BorderLayout.SOUTH);

        this.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if (EssPanel.this.isDrawingRect) {
                    EssPanel.this.currentShape = new Rect(e.getX(), e.getY(), e.getX(), e.getY());

                }
                else if (EssPanel.this.isDrawingOval) {
                    EssPanel.this.currentShape = new Oval(e.getX(), e.getY(), 0, 0);

                }
                else if (EssPanel.this.isDrawingLine) {
                    EssPanel.this.currentShape = new Line(e.getX(), e.getY(), e.getX(), e.getY());

                }else if (EssPanel.this.isFreehandDrawing) {
                    EssPanel.this.freehandPoints.add(new Point(e.getX(), e.getY()));
                    EssPanel.this.repaint();
                }
                else if (EssPanel.this.isErasing) {
                    EssPanel.this.setDrawColor(Color.BLACK);
                    int circleRadius = 5;
                    EssPanel.this.shapes.add(new Eraser(e.getX(), e.getY(), circleRadius));
                    EssPanel.this.repaint();
                }

            }

            public void mouseReleased(MouseEvent e) {
                if (EssPanel.this.isFreehandDrawing) {
                    if (!EssPanel.this.freehandPoints.isEmpty()) {
                        EssPanel.this.shapes.add(new FreeHand(new ArrayList(EssPanel.this.freehandPoints), EssPanel.this.drawColor));
                        EssPanel.this.freehandPoints.clear();
                        EssPanel.this.repaint();
                    }
                } else if (EssPanel.this.currentShape != null) {
                    if (EssPanel.this.filledCheckBox.isSelected() && (EssPanel.this.currentShape instanceof Rect || EssPanel.this.currentShape instanceof Oval )) {
                        ((Filling) EssPanel.this.currentShape).setFilled(true);
                    }

                    EssPanel.this.currentShape.setDotted(EssPanel.this.isDottedLine);
                    EssPanel.this.currentShape.setColor(EssPanel.this.drawColor);
                    EssPanel.this.shapes.add(EssPanel.this.currentShape);
                    EssPanel.this.currentShape = null;
                    EssPanel.this.repaint();
                }

            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if (EssPanel.this.isErasing) {
                    int circleRadius = 10;
                    EssPanel.this.shapes.add(new Eraser(e.getX(), e.getY(), circleRadius));
                    EssPanel.this.repaint();
                } else if (EssPanel.this.isFreehandDrawing) {
                    EssPanel.this.freehandPoints.add(new Point(e.getX(), e.getY()));
                    EssPanel.this.repaint();
                } else if ((EssPanel.this.isDrawingLine || EssPanel.this.isDrawingRect || EssPanel.this.isDrawingOval) && EssPanel.this.currentShape != null) {
                    if (EssPanel.this.currentShape instanceof Line) {
                        ((Line) EssPanel.this.currentShape).setEndX(e.getX());
                        ((Line) EssPanel.this.currentShape).setEndY(e.getY());
                    }  else if (EssPanel.this.currentShape instanceof Oval) {
                        ((Oval) EssPanel.this.currentShape).setWidth(e.getX());
                        ((Oval) EssPanel.this.currentShape).setHight(e.getY());
                    } else if (EssPanel.this.currentShape instanceof Rect) {
                        ((Rect) EssPanel.this.currentShape).setWidth(e.getX());
                        ((Rect) EssPanel.this.currentShape).setHight(e.getY());
                    } else if (EssPanel.this.isErasing) {
                        int circleRadius = 5;
                        EssPanel.this.shapes.add(new Eraser(e.getX(), e.getY(), circleRadius));
                        EssPanel.this.repaint();
                    }
                    EssPanel.this.repaint();
                }

            }

            public void mouseMoved(MouseEvent e) {
            }
        });
    }

    private void setDrawColor(Color color) {
        this.drawColor = color;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(Shape shape : this.shapes) {
            shape.draw(g);
        }

        if ((this.isDrawingLine || this.isDrawingRect || this.isDrawingOval) && this.currentShape != null) {
            this.currentShape.setDotted(this.isDottedLine);
            this.currentShape.draw(g);
        } else if (this.isFreehandDrawing) {
            FreeHand freeHand = new FreeHand(new ArrayList(this.freehandPoints));
            freeHand.setColor(this.drawColor);
            freeHand.draw(g);
        }

    }

    private void clearFreehandPoints() {
        this.freehandPoints.clear();
        this.repaint();
    }

    public void clearDrawings() {
        this.shapes.clear();
        this.repaint();
    }

    private void setUniformSize(JComponent component, Dimension size) {
        component.setPreferredSize(size);
        component.setMaximumSize(size);
        component.setMinimumSize(size);
    }
}
