import java.lang.*;
import java.util.*;
import java.io.*;
class CRC
{
public static void main(String args[]) throws IOException
{
CRCFN obj=new CRCFN();
obj.send();
obj.receive();
}
}
class CRCFN
{
int n,h,l,dl;
char p[]=new char[50];
char t[]=new char[50];
char om[]=new char[50];
char dv[]=new char[50];
char r[]=new char[50];
int i,j,k,z;
String str1=new String("");
String str2=new String("");
String poly=new String("");
String tempdiv=new String("");
void send() throws IOException
{
BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
System.out.println("enter crc to be used");
System.out.println("enter 12 for crc -12 \n 160 for crc 16\n 161 for crc-16-cit\n");
n=Integer.parseInt(br.readLine());
if(!((n==12||(n==160)||(n==161))))
{
System.out.println("invalid");
System.exit(0);
}
System.out.println("\nat sender side");
System.out.println("-----------------");
System.out.println("\n enter the original frame");
str1=br.readLine();
om=str1.toCharArray();
for(int i=0;i<om.length;i++)
System.out.print(om[i]);
h=om.length;
str2+=str1;
System.out.println("\n lenght and original message\n"+h+"\t\n"+str2);
if(n==12)
{
poly+=("1100000001111");
tempdiv+=("0000000000000");
str2+=("000000000000");
System.out.println(poly+"   \n"+tempdiv+"    \n" + str2);
}
else if(n==160)
{
poly+=("11000000000000101");
tempdiv+=("00000000000000000");
str2+=("000000000000");
System.out.println(poly+"   \n"+tempdiv+"      \n" +str2);
}
else if(n==161)
{
poly+=("1000100000010001");
tempdiv+=("00000000000000000");
str2+=("0000000000000000");
System.out.println(poly+"   \n" +tempdiv+"   \n"+str2);
}
else
{
System.out.println("invalid");
System.exit(0);
}
dv=str2.toCharArray();
p=poly.toCharArray();
t=tempdiv.toCharArray();
l=p.length;
dl=dv.length;
System.out.println("\nlenght of polynamial and dividend\n"+l+"\t\n  "+dl);
System.out.println("\naugmented didvdend\n");
for(int i=0;i<dv.length;i++)
System.out.print(dv[i]);
System.out.println("\n");
System.out.println("\ngenerator\n");
for(int i=0;i<p.length;i++)
System.out.print(p[i]);
System.out.println("\n");
System.out.println("\ntemp data\n");
for(int i=0;i<t.length;i++)
System.out.print(t[i]);
System.out.println("\nthe divisor for each step\nof modulo-2");
for(i=0;i<h;i++)
{
if(dv[i]=='0')
{
for(j=i,k=0;j<(l+i)&&k<l;j++,k++)
{
if(dv[j]==t[k])
dv[j]='0';
else
dv[j]='1';
}
}
else if(dv[i]=='1')
{
for(j=i,k=0;j<(l+i)&&k<l;j++,k++)
{
if(dv[j]==p[k])
dv[j]='0';
else
dv[j]='1';
}
}
else if((dv[i]!='0')||(dv[i]!='1'))
{
System.out.println("invalid frame");
System.exit(0);
}
for(z=i;z<(l+i);z++)
System.out.print(dv[z]);
System.out.println("\n");
}
for(i=h,j=0;i<=(dl-1);i++,j++)
r[j]=dv[i];
String rstr=new String(r);
System.out.println("\nthe reminder is\n"+rstr);
str1+=rstr;
System.out.println("\n the frame sent is:\n"+str1);
}
void receive()throws IOException
{
//char om[50],dv[50],r[20];
char om[]=new char[50];
char dv[]=new char[50];
char r[]=new char[50];
int i,j,k,z,c=0;
String omstr=new String("");
BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
System.out.println("\n at the receiver\n");
System.out.println("...................\n");
System.out.println("\n enter the frame received:\n");
omstr=br.readLine();
om=omstr.toCharArray();
for(i=0;i<om.length;i++)
dv[i]=om[i];
//strcpy(dv,om);
System.out.println("\n the divisor for each step \n of modulo-2 division is:\n");
for(i=0;i<=h-1;i++)
{
if(dv[i]=='0')
{
for(j=i,k=0;j<(l+i)&&k<l;j++,k++)
{
if(dv[j]==t[k])
dv[j]='0';
else
dv[j]='1';
}
}
else if(dv[i]=='1')
{
for(j=i,k=0;j<(l+i)&&k<l;j++,k++)
{
if(dv[j]==p[k])
dv[j]='0';
else
dv[j]='1';
}
}
else
{
System.out.println("invalid frame\n");
System.exit(0);
}
for(z=i;z<13+i;z++)
System.out.print(dv[z]);
System.out.println("\n");
}
for(i=h,j=0;i<=(dl-1);i++,j++)
{
r[j]=dv[i];
if(r[j]=='0')
c++;
}
//r[j]='\0';
System.out.println("\n the remainder is\n");
for(int q=0;q<r.length;q++)
System.out.print(r[q]);
System.out.println("\n\n\n"+c);
if(c==l-1)
{
System.out.println("\n the frame received is error free:\n");
//om[h]='\0';
System.out.println("\n the original frame is \n");
for(int q=0;q<om.length;q++)
System.out.print(om[q]);
System.out.println("\n the original message is \n");
for(int x=0;x<h;x++)
System.out.print(om[x]);
}
else
System.out.println("\n received frame is corrupted:FRAME HAVE ERRORS\n");
}
}
