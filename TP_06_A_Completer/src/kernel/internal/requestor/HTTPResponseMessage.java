package kernel.internal.requestor;

import java.util.Hashtable;

import  util.TagsLib;

public class HTTPResponseMessage implements Message {
	private Hashtable _stack;

	public HTTPResponseMessage(String result) {
		try {

			System.out.println("NEW HTTPResponseMessage");

			_stack = new Hashtable();
			_stack.put(TagsLib.RESULT, "" + result);
		} catch (Exception ex) {
			System.out
					.println("Impossible de creer le message HTTPResponseMessage "
							+ ex.toString());
		}
	}

	public String getMessageType() {
		return TagsLib.RESP;
	}

	public Hashtable getQueryData() {
		return this._stack;
	}

	public String toString() {
		try {
			return "" + _stack.get(TagsLib.RESULT);
		} catch (Exception e) {
			System.out.println("Exception @ HTTPResponseMessage.toString " + e) ;
			return e.toString();
		}
	}

	public byte[] getBytes() {
		byte[] valRet;

		try {
			valRet = ("" + _stack.get(TagsLib.RESULT)).getBytes();
		} catch (Exception e) {
			System.out.println("Exception @ HTTPResponseMessage.getBytes " + e) ;
			valRet = e.getMessage().getBytes();
		}

		return valRet;
	}
}
