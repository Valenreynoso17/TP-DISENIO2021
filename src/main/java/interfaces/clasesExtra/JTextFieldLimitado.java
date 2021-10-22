package main.java.interfaces.clasesExtra;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class JTextFieldLimitado extends PlainDocument{
  private int limit;
  
  private DocumentFilter filter = new UppercaseDocumentFilter();

  public JTextFieldLimitado(int limit) {
   super();
   this.limit = limit;
   }
  
  public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
    if (str == null) return;

    if ((getLength() + str.length()) <= limit) {
      super.insertString(offset, str.toUpperCase(), attr);
    }
  }
  
  
}