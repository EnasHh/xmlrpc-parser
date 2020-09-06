package xmlRpc.parser;

import java.util.ArrayList;
import java.util.List;

public class Struct {
	
	private List<Member> members = new ArrayList<Member>();

	public List<Member> getMembers() {
		return members;
	}

	public void addMember(Member member) {
		members.add(member);

	}
}
