import java.io.*;
import java.util.regex.*;
import java.io.File;
public class PosTagProcessor{
	/*
	 *This class extracts individual files from the input file and processes them to get
	 *all NNP-groups
	 *Then it saves the processed information in another file merged_nnp processed.txt
	 *which will be used as input by the NER tagger!!
	 */
	public static void main(String args[])throws IOException{
		int pp1;

		byte[] buffer = new byte[(int) new File("merged_pos_out.txt").length()];
BufferedInputStream f = new BufferedInputStream(new FileInputStream("merged_pos_out.txt"));





f.read(buffer);
String str= new String(buffer);//contains output of pos tagger

FileOutputStream out= new FileOutputStream("merged_nnp_processed.txt"); // declare a file output object
                PrintStream p= new PrintStream( out );
String pstr;
String FF[]=new String[10000];
//System.out.println(str);

String regex="([\\w&&[^\\s]]{1,}_(NNP||NNPS) ){1,}+" ;
String file_reg="START786(.){1,}?END786";
Matcher files=Pattern.compile(file_reg,Pattern.DOTALL).matcher(str);
Matcher mkey;
Matcher m1;
String single_file_text;
String regex_key;
int fNo=0;
int key[]=new int[500];
String prod[][]=new String[500][3];//contains top 3 prods
int maxp[][]=new int[500][3];//maxp[0]=maxp[1]=maxp[2]=0;// max contains freq of top 3 organizations
int dk=0;
while(files.find()){

	single_file_text=files.group();
	//System.out.println(single_file_text);
	//mkey=Pattern.compile(regex_key).matcher(str);
	m1= Pattern.compile(regex).matcher(single_file_text);
	//other file String regex_key="[tT]he [Gg]roup|profit|loss|r[oi]se|f[ea]ll| share[s]| facilit|quarter|stake|stock|top_|industr|growth|company|financial|venture|fiscal|corporat|asset";

	regex_key="[tT]he_DT [Gg]roup| [Gg]roup_| profit| loss_| r[oi]se_|market| f[ea]ll_|merger_| solution|strateg| facilit|announce|capacity| [Bb]oard | deal_NN |product| share_| [Mm]eeting_| capital_| shares_|acquire|business_|sector| quarter_| stake_| stock_| growth_| company_| top_| industr|financial_| venture_| fiscal_| corporat| major_| asset_| assets_| bid| invest| subsidiary| unit| retail| manufactur| sale";
    mkey=Pattern.compile(regex_key).matcher(single_file_text);
pstr=new String(single_file_text);
 key[fNo]=0;
while(mkey.find()){
		key[fNo]++;
}
fNo++;
int c=0;

while(m1.find())
{
FF[c++]=m1.group();

}

//process the NPPs
for(int i=0;i<c;i++)
{

	FF[i]=FF[i].replaceAll("_NNPS","").replaceAll("_NNP","");
	//System.out.println(FF[i]);

}
String lists="([Mm]onday)|([Tt]uesday)|([Ww]ednesday)|([Tt]hursday)|([Ff]riday)|([Ss]aturday)|([Ss]unday)|([Ja]anuary)|([ff]ebruary)|([Mm]arch)|([Aa]pril)|([Mm]ay)|([Jj]une)|([Jj]uly)|([Aa]ugust)|([Ss]eptember)|([Oo]ctober)|([Nn]ovember)|([Dd]ecember)|(Crore)|(Arab)|(Million)|(Billion)|(Trillion)|(Thousand)|(Lakh)|(Hundred)|(Jan)|(Feb)|(Mar)|(Apr)|(Jun)|(Jul)|(Aug)|(Sep)|(Sept)|(Oct)|(Nov)|(Dec)";

      Matcher lst;
      Pattern patt=Pattern.compile(lists);
      for(int i=0;i<c;i++) {
      	lst=patt.matcher(FF[i]);

      if(lst.find())FF[i]="";
     // System.out.println(b);
      }

                	p.println(" 786 ");
		for(int i=0;i<c;i++){p.println("they told that "+FF[i]+" is something.");//System.out.println(FF[i]+"jgjgj");
		}
                    p.println(" 678 ");


//prod code starts here
{

String PP[]=new String[10000];


String regex1_prod="(POS| s_VBZ) ([^ ]{1,} ){0,3}(\\p{Upper}[^ ]{1,}){1,}[^ ]{1,}";//"(POS| s_VBZ)([^_\\p{Upper}]{1,}_){1,4}[^ ]{1,}( \\p{Upper}[^ ]{1,}){1,}[^ ]{1,}";//"(POS| s_VBZ) ([^ ]{1,} ){1,}(\\p{Upper}[^ ]{1,}){1,}[^ ]{1,}";//
String regex2_prod="PRP\\$([A-Za-z\\s]{1,}_NNP){1,}[^_]{1,}";
String regex3_prod="[Tt][Hh][Ee]_DT([^_]{1,}_NNP){1,}([^_]{1,}_NN){0,1}";

Matcher m= Pattern.compile(regex1_prod,Pattern.DOTALL).matcher(pstr);
Matcher m2= Pattern.compile(regex2_prod).matcher(pstr);
Matcher m33=Pattern.compile(regex3_prod).matcher(pstr);
int cc=0;

while(m2.find())
{
PP[cc++]=((m2.group()).toString()).replaceAll("PRP\\$ ","").replaceAll("_NN[PS]{1,2}","").replaceAll(" [a-z]{1,}","");
//System.out.println(PP[cc-1]);
}


while(m.find())
{
PP[cc++]=m.group(3).replaceAll("_[\\w\\W]{1,}","");
//System.out.println(PP[cc-1]);
}
while(m33.find())
{
	PP[cc++]=m33.group().replaceAll("[Tt]he_DT ","").replaceAll(" [\\w]{1,}_NN","").replaceAll("_NN[PS]{0,3}","");
	//System.out.println(PP[cc-1]);
}

int x=0;
int freq2[][]=new int[500][500];
for(;x<500;x++)freq2[dk][x]=1;


for(int i=0;i<cc;i++){
	if(PP[i]=="")continue;

	for(int j=i+1;j<cc;j++){
	     if(PP[j]=="")continue;

		try{
			if((PP[i].toLowerCase()).contains(PP[j].toLowerCase())){
			PP[j]="";
			freq2[dk][i]++;


		}
		}
		catch(NullPointerException e){
			System.out.println("AAARRRGGGGHHHH");
		}
}
}


int g=0;pp1=0;
for(int l=1;l<=3;l++)
{
	for(int k=0;k<cc;k++){
	if(freq2[dk][k]>g){g=freq2[dk][k];
	maxp[dk][pp1]=freq2[dk][k];
	prod[dk][pp1]=PP[k];
	freq2[dk][k]=0;
	}

	}g=0;pp1++;
}dk++;

}
//code block for prod ends



	}
	p.close();
	//for(int i=0;i<fNo;i++)System.out.println("keyword count= "+i+"  "+key[i]);
//SENDING keyword freq 2 file
FileOutputStream out1; // declare a file output object
                PrintStream p1; // declare a print stream object

                        // Create a new file output stream
                        // connected to "myfile.txt"
                        out1 = new FileOutputStream("Keyword.txt");

                        // Connect print stream to the output stream
                        p1 = new PrintStream( out1 );
		for(int i=0;i<fNo;i++){
                        p1.println ("1:"+key[i]+" 2:"+maxp[i][0]+" 3:"+maxp[i][1]+" 4:"+maxp[i][2]);
		}
                        p1.close();

//System.out.println(fNo+"  "+dk);



}



}