package hello;

import java.io.IOException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;



public class POST2GCM {
	
    private Message message;
	

    public boolean sendMessage(final String pRegistrationID, final String pApiKey, String groupId) {
        final ProxySender sender = new ProxySender(pApiKey);
        System.out.println("ClientRegistrationID " + pRegistrationID);
        System.out.println("GCMServerApiKey " + pApiKey);
        message = new Message.Builder().addData("REGISTRATION_ID", pRegistrationID).addData("groupId", groupId).build();
        
        try {
            final Result result = sender.send(message, pRegistrationID, 5);
            System.out.println("MessageID " + result.getMessageId());
            System.out.println("ErrorCode " + result.getErrorCodeName());
            if (result.getMessageId() != null && result.getErrorCodeName() == null) {
                return true;
            } else {
                if (result.getErrorCodeName().equals("NotRegistered")) {                    
                    System.out.println("Not Registered");
                }
                return false;
            }
        } catch (final IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
	

    
        
}