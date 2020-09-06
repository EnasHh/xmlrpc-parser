package xmlRpc.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

public class XStreamParser {

	//
	static Map<String, Object> requestMap = new HashMap<String, Object>();

	public static Map<String, Object> getRequestMap() {
		return requestMap;
	}

	public static Object[] parse(String xmlrpcReq) {
		Object[] params = null;
		try {

			XStream xStream = new XStream();
			xStream.alias("methodCall", MethodCall.class);
			xStream.alias("methodResponse", MethodResponse.class);
			xStream.alias("params", Params.class);
			xStream.alias("param", Param.class);
			xStream.alias("struct", Struct.class);
			xStream.alias("member", Member.class);
			xStream.alias("value", Value.class);
			xStream.alias("value", Member.Value.class);
			xStream.addImplicitCollection(Struct.class, "members");
			xStream.addImplicitCollection(Member.Value.Array.Data.class, "values");
			xStream.aliasField("int", Member.Value.class, "intt");
			xStream.aliasField("dateTime.iso8601", Member.Value.class, "date");
			xStream.aliasField("boolean", Member.Value.class, "bool");

			// System.out.println(xmlrpcReq);
			MethodResponse objConverted = (MethodResponse) xStream.fromXML(xmlrpcReq);
			// MethodCall objConverted = (MethodCall) xStream.fromXML(xmlrpcReq);
			requestMap = mapping(objConverted.params.param.value.struct);
			params = new Object[] { requestMap };

			System.out.println(Arrays.toString(params));

		} catch (Exception e) {

			e.printStackTrace();
		}

		return params;

	}

	public static Map<String, Object> mapping(Struct obj) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, Object> temp = new HashMap<String, Object>();
		for (Member member : obj.getMembers()) {
			temp = mapOfMembers(member);
			requestMap.putAll(temp);
		}

		return requestMap;
	}

	public static Map<String, Object> mapOfMembers(Member member) {
		Map<String, Object> temp = new HashMap<String, Object>();
		String name = member.getName();
		Object value = listOfValues(member.value);
		temp.put(name, value);
		return temp;

	}

	public static Object listOfValues(Member.Value value) {
		List<Object> values = new ArrayList<Object>();

		Map<String, Object> mem = new HashMap<String, Object>();
		if (value.string != null) {
			Object val = value.getString();
			return val;
		} else if (value.bool != null) {
			boolean b;
			if (value.getBool().equals("1"))
				b = true;
			else
				b = false;
			Object val = b;

			return val;

		} else if (value.date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'T'HH:mm:ssZ");
			try {
				Date date = formatter.parse(value.getDate());
				Object val = date;
				return val;
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else if (value.i4 != null) {
			Object val = value.i4;
			return val;

		} else if (value.intt != null) {
			Object val = value.intt;
			return val;

		} else if (value.array != null) {

			for (Member.Value element : value.array.data.getValues()) {

				values.add(listOfValues(element));

			}
			Object[] valuess = new Object[values.size()];

			valuess = values.toArray();

			return valuess;
		} else if (value.getStruct() != null) {

			// check this logic
			for (Member element : value.getStruct().getMembers()) {
				mem.putAll(mapOfMembers(element));

			}
			return mem;
		}

		return null;

	}

}
