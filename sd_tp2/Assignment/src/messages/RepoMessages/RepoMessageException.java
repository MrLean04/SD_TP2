package messages.RepoMessages;

public class RepoMessageException extends RuntimeException {
        private RepoMessage msg;
    
        /**
         * Instantiation of Lounge message exception.
         * @param error text with error
         * @param msg message with exception
         */ 
        public RepoMessageException(String error, RepoMessage msg) {
            super(error);
            this.msg = msg;
        }
    
        /**
         * Obtaining the Lounge message with error.
         * @return message
         */
        public RepoMessage getMsg() {
            return msg;
        }
        
    }
