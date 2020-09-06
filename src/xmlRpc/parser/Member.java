package xmlRpc.parser;

import java.util.ArrayList;
import java.util.List;

public class Member {

	String name;
	Value value;

	public String getName() {
		return name;
	}

	public Value getValue() {
		return value;
	}

	class Value {
		String string;

		public String getString() {
			return string;
		}

		String bool;// boolean

		public String getBool() {
			return bool;
		}

		String i4;

		public String getI4() {
			return i4;
		}

		String address;

		public String getAddress() {
			return address;
		}

		String intt;

		public String getIntt() {
			return intt;
		}

		String date;

		/*
		 * DateTimeFormatter formatter =
		 * DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"); DateTime dt =
		 * formatter.parseDateTime(string);
		 */
		public String getDate() {
			return date;
		}

		Array array;

		public Array getArray() {
			return array;
		}

		class Array {

			Data data;

			public Data getData() {
				return data;
			}

			class Data {

				private List<Value> values = new ArrayList<Value>();

				public List<Value> getValues() {
					return values;
				}

				public void addValue(Value val) {
					values.add(val);

				}

			}

		}
		
		Struct struct;

		public Struct getStruct() {
			return struct;
		}
		

		/*_Struct struct;

		public _Struct getStruct() {
			return struct;
		}

		class _Struct {
			Member member;

			public Member getMember() {
				return this.member;
			}

		}*/
	}

}
