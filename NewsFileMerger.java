import java.io.*;
import java.io.File;
import java.util.regex.*;
class NewsFileMerger{
	//merges all news in the directory to produce a single file called merge.txt
	//run pos script after running this
	public static void main(String args[])throws IOException{

	byte[] buffer;
//BufferedInputStream bis;= new BufferedInputStream(new FileInputStream("newsout.txt"));
//f.read(buffer);
//String str= new String(buffer);
File temp,btmp;
String str;
File f=new File("news");

System.out.println(f.length());
File list[]=f.listFiles();
BufferedInputStream bis;
String wrd_reg="\\b";
Pattern pp=Pattern.compile("\\b");
Matcher m;
FileOutputStream out = new FileOutputStream("merge.txt");
PrintStream p = new PrintStream( out );
out=new FileOutputStream("word_count.txt");
PrintStream p2=new PrintStream(out);
int w;
for(int i=0;i<list.length;i++)
{w=0;
	temp=new File(list[i].toString());
	buffer= new byte[(int) temp.length()];
    bis= new BufferedInputStream(new FileInputStream(list[i]));
    bis.read(buffer);
    str=new String(buffer);
    m=pp.matcher(str);
    while(m.find())w++;

    p.print(" START786 ");
    p.print("  "+list[i]+"  ");
     p.print(str);
     p.print(" END786 ");
System.out.println(list[i]+"  "+w/2);
p2.print(w/2+" ");
}
p.close();


}
}