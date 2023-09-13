import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;  //declaring a frame

    JMenuBar menuBar; // declaring a menu

    JMenu file, edit;

    JMenuItem  newFile, openFile, saveFile;

    JMenuItem  copy, cut, paste, selectAll, close;

    JTextArea textArea;


    //Constructor
    public TextEditor() {

        frame = new JFrame();  //initializes a frame

        menuBar = new JMenuBar(); //initializes a Menu

        textArea = new JTextArea();  //initializes the textArea

        file = new JMenu("File");

        edit = new JMenu("Edit");

        newFile = new JMenuItem("New Window-Ctrl+N");

        openFile = new JMenuItem("Open File-Ctrl+O");

        saveFile = new JMenuItem("Save File-Ctrl+S");

        newFile.addActionListener(this);//add action listener to menu items

        openFile.addActionListener(this);

        saveFile.addActionListener(this);

        file.add(newFile);

        file.add(openFile);

        file.add(saveFile);

        cut = new JMenuItem("Cut-Ctrl+X");

        copy = new JMenuItem("Copy-Ctrl+C");

        paste = new JMenuItem("Paste-Ctrl+V");

        selectAll = new JMenuItem("SelectAll-Ctrl+A");

        close = new JMenuItem("Close-Ctrl+W");

        cut.addActionListener(this);

        copy.addActionListener(this);

        paste.addActionListener(this);

        selectAll.addActionListener(this);

        close.addActionListener(this);

        edit.add(cut);

        edit.add(copy);

        edit.add(paste);

        edit.add(selectAll);

        edit.add(close);


        menuBar.add(file);

        menuBar.add(edit);

        frame.setJMenuBar(menuBar);

        //create a content panel

        JPanel panel =new JPanel();

        panel.setBorder(new EmptyBorder(5,5,5,5));

        panel.setLayout(new BorderLayout(0,0));

        panel.add(textArea,BorderLayout.CENTER);

        //create a scroll pan

        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollPane);

        frame.add(panel);



        //set the dimensions for the frame

        frame.setBounds(200, 200, 500, 500);

        frame.setTitle("Text Editor");

        frame.setVisible(true);  // frame is visible

        frame.setLayout(null);
    }
    @Override
   public void  actionPerformed(ActionEvent actionEvent){

        if(actionEvent.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy) {
            //perform copy operation
            textArea.copy();
        }

        if(actionEvent.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //perform select all operation

            textArea.selectAll();
        }

        if(actionEvent.getSource()==close){
            //perform close operation

            System.exit(0);
        }

        if(actionEvent.getSource()==openFile){

            //open a fileChooser

            JFileChooser fileChooser=new JFileChooser("C:");

            int chooseOption=fileChooser.showOpenDialog(null);

            //if we clicked the open button

            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting the selected file

                File file1=fileChooser.getSelectedFile();

                //get the selected path

                String filePath = file1.getPath();

                try{

                    //initializes a  fileReader

                    FileReader fileReader=new FileReader(filePath);
                    // initializes bufferedReader

                    BufferedReader bufferedReader=new BufferedReader(fileReader);

                    String  intermediate="", output="";

                    //read contents libe by line

                    while((intermediate=bufferedReader.readLine())!=null){

                        output+=intermediate+"\n";
                    }

                    //set the output to text Area

                    textArea.setText(output);

                } catch (IOException fileNotFoundException) {

                    fileNotFoundException.printStackTrace();

                }


            }
        }
        if(actionEvent.getSource()==saveFile){

            //open a fileChooser

            JFileChooser fileChooser=new JFileChooser("C:");

            int chooseOption=fileChooser.showSaveDialog(null);

            //if we clicked the save button

            if(chooseOption==JFileChooser.APPROVE_OPTION){

                File file1 = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");

                try{
                    FileWriter fileWriter=new FileWriter(file1);
                    //
                    BufferedWriter bufferedWriter =new BufferedWriter(fileWriter);

                    //write instance of txt area

                    textArea.write(bufferedWriter);

                    bufferedWriter.close();
                }
                catch (IOException ioException){

                    ioException.printStackTrace();

                }
            }
        }

        if(actionEvent.getSource()==newFile){
            TextEditor txtEditor=new TextEditor();
        }

    }
    public static void main(String[] args) {

        TextEditor txt = new TextEditor();

    }
}