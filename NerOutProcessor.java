import java.io.*;
import java.util.regex.*;
import java.io.File;
public class NerOutProcessor{
public static 	String top_org_for_each[][]=new String[500][3];
public static int top_freq_for_each[][]=new int[500][3];

	public static void main(String args[])throws IOException{

	String SS[]=new String[10000];
		//nw run NER frm cmd...
// now remove the LOCATION from the file output of NER on RUN.TXT
String single_ner_out;
byte[] buffer2 = new byte[(int) new File("ner_out.txt").length()];
BufferedInputStream ff = new BufferedInputStream(new FileInputStream("ner_out.txt"));
ff.read(buffer2);
String str2= new String(buffer2);
String file_reg="786/O(.){1,}?678/O";
Matcher files=Pattern.compile(file_reg,Pattern.DOTALL).matcher(str2);

int fNo=0;

while(files.find()){
	single_ner_out=files.group();
	//System.out.println(single_file_text);
	//mkey=Pattern.compile(regex_key).matcher(str);


String regex3="([A-Z][A-Za-z0-9]{1,}/ORGANIZATION ){1,}";
Matcher m3= Pattern.compile(regex3).matcher(single_ner_out);
int c=0;
while(m3.find())
{
	SS[c++]=m3.group();

	SS[c-1]=SS[c-1].replaceAll("/ORGANIZATION","");


}

int x=0;
int freq[]=new int[500];
for(;x<500;x++)freq[x]=1;

int i;
for(i=0;i<c;i++){
	if(SS[i]=="")continue;

	for(int j=i+1;j<c;j++){
	     if(SS[j]=="")continue;

		try{if((SS[i].toLowerCase()).contains(SS[j].toLowerCase())){
			SS[j]="";
			freq[i]++;


		}}
		catch(NullPointerException e){
			System.out.println("AAARRRGGGGHHHH");
		}
}



}

System.out.println("----------------------");
int g=0;
int p1=0;
String org[]=new String[3];//contains top 3 organizations
int max[]=new int[3];max[0]=max[1]=max[2]=0;// max contains freq of top 3 organizations
//String prod[]=new String[3];//contains top 3 prods
//int maxp[]=new int[3];maxp[0]=maxp[1]=maxp[2]=0;// max contains freq of top 3 organizations

for(int l=1;l<=3;l++)
{
	for(int k=0;k<c;k++){
	if(freq[k]>g){g=freq[k];
	max[p1]=freq[k];
	org[p1]=SS[k];
	freq[k]=0;
	}

	}g=0;p1++;
}
System.out.println(org[0]+"  "+max[0]);
System.out.println(org[1]+"  "+max[1]);
System.out.println(org[2]+"  "+max[2]);
top_org_for_each[fNo]=org;
top_freq_for_each[fNo++]=max;
//System.out.println(top_freq_for_each[0][0]);

FileOutputStream out,out11; // declare a file output object
                PrintStream p,p11; // declare a print stream object

                        // Create a new file output stream
                        // connected to "myfile.txt"
                        out = new FileOutputStream("ORG.txt");
                        out11=new FileOutputStream("ORGname.txt");

                        // Connect print stream to the output stream
                        p = new PrintStream( out );
                        p11 = new PrintStream( out11 );
		for(i=0;i<fNo;i++){
                        p.println ("5:"+top_freq_for_each[i][0]+" 6:"+top_freq_for_each[i][1]+" 7:"+top_freq_for_each[i][2]);
                        p11.println(org[0]+" "+org[1]+" "+org[2]+" ");
		}
                        p.close();p11.close();


System.out.print(fNo);
	}

}

}