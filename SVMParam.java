import java.io.*;
import java.io.File;
import java.util.regex.*;

public class SVMParam {
public static void main(String args[]) throws IOException{
String word_count[]=new String[1000];
   	byte[] buffer = new byte[(int) new File("ORG.txt").length()];
BufferedInputStream f = new BufferedInputStream(new FileInputStream("ORG.txt"));
f.read(buffer);
String str= new String(buffer);

	byte[] buffer1 = new byte[(int) new File("Keyword.txt").length()];
BufferedInputStream f1 = new BufferedInputStream(new FileInputStream("Keyword.txt"));

f1.read(buffer1);
String str1= new String(buffer1);//contains output of pos tagger
Pattern wrd=Pattern.compile("[\\d]{1,} ");
Matcher mwrd;

String regex_org="[\\d]:[\\d]{1,} [\\d]:[\\d]{1,} [\\d]:[\\d]{1,}";
Matcher m=Pattern.compile(regex_org).matcher(str);
int cc=0;
String PP[]=new String[1000];
while(m.find())
{
PP[cc]=m.group();
//PP[cc]=PP[cc].substring(0,PP[cc].lastIndexOf(""));
System.out.println(PP[cc]+" q");
cc++;
}


String regex_keywrd="[\\d]:[\\d]{1,} [\\d]:[\\d]{1,} [\\d]:[\\d]{1,} [\\d]:[\\d]{1,}";
Matcher m2=Pattern.compile(regex_keywrd).matcher(str1);
int ccc=0;
String PPP[]=new String[1000];
while(m2.find())
{
PPP[ccc]=m2.group();
//PP[cc]=PP[cc].substring(0,PP[cc].lastIndexOf(""));
//System.out.println(PPP[ccc]+" q");
ccc++;
}
//System.out.println(cc+"  "+ccc);
//now write PP and PPP to a file...final file for SVM
FileOutputStream out1; // declare a file output object
                PrintStream p1; // declare a print stream object

                        // Create a new file output stream
                        // connected to "myfile.txt"
                        out1 = new FileOutputStream("SVMInput.txt");

                        // Connect print stream to the output stream
                        p1 = new PrintStream( out1 );


                        	 buffer = new byte[(int) new File("word_count.txt").length()];
         f = new BufferedInputStream(new FileInputStream("word_count.txt"));
f.read(buffer);
 str= new String(buffer);
 mwrd=wrd.matcher(str);
 int wc=0;
 String st;
  while(mwrd.find()){
  	word_count[wc]=""+mwrd.group();
  	System.out.println(word_count[wc]);
  	wc++;
}


		for(int i=0;i<cc;i++){
                       p1.println (PPP[i]+" "+PP[i]);//word_count[i]);
                      //else p1.println ("-1 "+PP[i]+" "+PPP[i]);//+" "+"wc"+word_count[i]);
		}
                        p1.close();



}}
