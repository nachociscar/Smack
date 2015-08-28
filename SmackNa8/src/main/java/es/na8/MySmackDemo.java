package es.na8;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.Collection;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;


public class MySmackDemo implements MessageListener {

public  String username="";//"iciscar.bull@gmail.com";
public  String userContact="";//"nachociscar@gmail.com";

public  String password="";//
public static String id="";
static ConnectionConfiguration config;

public static Chat chat;
public static XMPPConnection connection = null;
public static boolean free=false;




public static void main(String args[]){

    System.out.println("$> java -jar SmackNa8.jar piacount@gmail.com password userContact@gmail.com");
   
    MySmackDemo mySmackObj = new MySmackDemo();
    if(args.length == 3){
    	
    	mySmackObj.username = args[0];
    	mySmackObj.password = args[1];
    	mySmackObj.userContact = args[2];
    	
    }else{
    	System.out.println("Args needed");
    	return;
    }
    mySmackObj.connectTOGTalk();

    mySmackObj.displayBuddyList();
    System.out.println("-----");

            String msg="";

    try {

   mySmackObj.sendMessage("RaspBerry Pi Connected", mySmackObj.userContact);
   System.out.println("Pulsa una tecla para terminar");
   System.in.read();
   
    }
    catch(Exception e){
        System.out.println("sending failed");
        e.printStackTrace();
    }

}



private  void connectTOGTalk(){

    try {
        //establish connection between client and server.
    	

    	ConnectionConfiguration config = new ConnectionConfiguration("talk.google.com", 5222, "googlemail.com");
    	 connection = new XMPPConnection(config);
    	
    	
       //connection = new  XMPPConnection(config);
  
        connection.connect();
        System.out.println("Connected to " + connection.getHost());
        //call base class function to get login
        connection.login(username,password);

        System.out.println(connection.isAuthenticated());

        Presence presence = new Presence(Presence.Type.available);
        connection.sendPacket(presence);
        System.out.println("presence is:" + presence.toXML());

    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

/*
 * Function to send message to specified user
 */
public  void sendMessage(String message, String to) 
{
    System.out.println("Message is:"+message);
    Chat chat = connection.getChatManager().createChat(to, this);
    try {
		chat.sendMessage(message);
	} catch (XMPPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

            System.out.println("Chat obj is:"+ chat);
}
/**
 * Function to display user list
 */
public void displayBuddyList()
{
Roster roster = connection.getRoster();
roster.setSubscriptionMode(Roster.SubscriptionMode.manual);
Collection<RosterEntry> entries = roster.getEntries();

System.out.println("\n\n" + entries.size() + " buddy(ies):");
for(RosterEntry r:entries)
{
System.out.println(r.getUser());
System.out.println(r.getName());
}
}

public void processMessage(Chat chat, Message message)
{
if(message.getType() == Message.Type.chat)
System.out.println(chat.getParticipant() + " says: " + message.getBody());
	if(message.getBody() !=null){
		String accion = message.getBody();
		if(accion.equalsIgnoreCase("IP")){
			Run run = new Run();
			String ip= run.getIP();
			sendMessage("The IP:"+ip, userContact );
			}else{
			sendMessage("You are talking with a raspberry", userContact );
			}
	}
	
}
}
