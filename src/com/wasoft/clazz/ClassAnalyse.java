package com.wasoft.clazz;
import java.io.FileInputStream;

public class ClassAnalyse {

	static final int CONSTANT_Utf8 = 1;
	static final int CONSTANT_Integer = 3;
	static final int CONSTANT_Float = 4;
	static final int CONSTANT_Long = 5;
	static final int CONSTANT_Double = 6;
	static final int CONSTANT_Class = 7;
	static final int CONSTANT_String = 8;
	static final int CONSTANT_Fieldref = 9;
	static final int CONSTANT_Methodref = 10;
	static final int CONSTANT_InterfaceMethodref = 11;
	static final int CONSTANT_NameAndType  = 12;
	
	static final int ACC_PUBLIC = 0x0001;
	static final int ACC_PRIVATE = 0x0002;
	static final int ACC_PROTECTED = 0x0004;
	static final int ACC_STATIC = 0x0008;
	static final int ACC_FINAL = 0x0010;
	static final int ACC_SUPER = 0x0020;
	static final int ACC_SYNCHRONIZED = 0x0020;	
	static final int ACC_VOLATILE = 0x0040;
	static final int ACC_TRANSIENT = 0x0080;
	static final int ACC_NATIVE = 0x0100;
	static final int ACC_INTERFACE = 0x0200; 
	static final int ACC_ABSTRACT = 0x0400;
	static final int ACC_STRICT = 0x0800;
	
	private static String hexString = "0123456789ABCDEF"; 
	static class ConstantPool{
		String type;
		String value;
		String rValue;
		ConstantPool(String type, String value)
		{
			this.type = type;
			this.value = value;
		}
	}
	
	static ConstantPool [] cp;
	static int currIndex = -1;
	static byte[] data;
	
	public static void main(String[] args) {
		String fileName = "D:\\My Data\\My Documents\\eclipse\\workspace\\test\\FileHandle.class";
		fileName = "c:\\BasicOperation.class";
        if (args.length == 1 )
        {
        	fileName = args[0];
        }
        try{

        	FileInputStream fis = new FileInputStream(fileName);

        	data = new byte[fis.available()];
        	
        	//读取文件到字节数组
        	fis.read(data);
        	fis.close();
        	
        	//解析文件数据
        	parseFile(data);
        }
        catch(Exception e){

        	System.out.println(e); 

        } 
    }
	 private static int toInt(byte b) {
		if (b >= 0)
			return (int) b;
		else
			return (int) (b + 256);
	}

	private static int toInt(String b) {
		int i = Integer.parseInt(b);
		return i >= 0 ? i : (i + 256);
	}
		
	private static int getInt()
	{
		return getInt(2); 
	}
	private static int getInt(int byteNumber)
	{
		if (byteNumber == 1)
		{
			return toInt(data[++currIndex]);
		}
		else if(byteNumber == 2)
		{
			return (toInt(data[++currIndex]) << 8) + toInt(data[++currIndex]);
		}
		else if(byteNumber == 4)
		{
			return (toInt(data[++currIndex]) << 24) + (toInt(data[++currIndex]) << 16) + (toInt(data[++currIndex]) << 8) + toInt(data[++currIndex]);
		}
		else
		{
			return -1;
		}
			
	}
	/*
	 * major  minor 	Java platform version 
	  	45       3           1.0 
		45       3           1.1 
		46       0           1.2 
		47       0           1.3 
		48       0           1.4 
		49       0           1.5 
		50       0           1.6 
	 */
	private static void parseFile(byte[] data) throws Exception{
		System.out.print("魔数(magic):0x");
		System.out.print(Integer.toHexString(data[++currIndex]).substring(6).toUpperCase());
		System.out.print(Integer.toHexString(data[++currIndex]).substring(6).toUpperCase());
		System.out.print(Integer.toHexString(data[++currIndex]).substring(6).toUpperCase());
		System.out.println(Integer.toHexString(data[++currIndex]).substring(6).toUpperCase());
		
		// 主版本号和次版本号码
		int minor_version = getInt();
		int major_version = getInt();
		System.out.println("版本号(version):" + major_version + "." + minor_version);
		
		//常量池
		int constant_pool_count = getInt();
		System.out.println("常量池(constant_pool_count)" + (constant_pool_count - 1) + ":");

		cp = new ConstantPool[constant_pool_count];		
		for (int i = 1; i < constant_pool_count; i++)
		{
			if (loadConstanPool(data, i))
			{
				i++;
			}
		}
		printCp();//打印常量池信息
		
		int af = getInt();
		System.out.println("访问标志(access_flag): 0x" + Integer.toHexString(af) + "," + getAccessFlag(af));
		
		int this_class_index = getInt();
		System.out.println("this_class:" + getClassName(this_class_index));
		
		int super_class_index = getInt();
		System.out.println("super_class:" + getClassName(super_class_index));
		
		int interfaces_count = getInt();
		System.out.println();
		System.out.println("接口(interfaces_count)" + interfaces_count + ":");
		for (int i = 0; i < interfaces_count; i++)
		{
			int interface_index = getInt();
			System.out.println(getClassName(interface_index));
		}
		
		int fields_count = getInt();
		System.out.println();
		System.out.println("字段(fields_count)" + fields_count + ":");
		for (int i = 0; i < fields_count; i++)
		{
			loadFields();
		}
		
		int methods_count = getInt();
		System.out.println();
		System.out.println("方法(methods_count)" + methods_count + ":");
		for (int i = 0; i < methods_count; i++)
		{
			loadFields();
		}
		
		int attributes_count = getInt();
		System.out.println();
		System.out.println("属性(attributes_count)" + attributes_count + ":");
		for (int i = 0; i < attributes_count; i++)
		{			
			loadAttributes();
			System.out.println("currIndex = " + currIndex);
		}
	} 
	
	private static void loadFields() throws Exception
	{
		int af = getInt();
		int name_index = getInt();		
		int descriptor_index = getInt();

		System.out.println("access_flag:" + getAccessFlag(af) + ",name: " + getString(name_index) + ",descriptor: " + getString(descriptor_index));

		int attributes_count = getInt();
		System.out.println("属性(" + attributes_count + "):");
		for (int i = 0; i < attributes_count; i++)
		{
			System.out.println("=================================================");
			loadAttributes();
		}
		
	}
	private static void loadAttributes() throws Exception
	{
		int attribute_name_index = getInt();
		String attribute_name = getString(attribute_name_index);
		System.out.println("attribute_name: " + attribute_name);
		int attribute_length = getInt(4);
		//for (int i = 0; i < attribute_length; i++)
		{
			if (attribute_name.equals("Code"))
			{
				System.out.println("Code:");
				int max_stack = getInt();
				int max_locals = getInt();
				System.out.println("max_stack = " + max_stack + ",max_locals = " + max_locals);
				
				int code_length = getInt(4);
				
				byte [] bytes = new byte [code_length];
				for (int j = 0; j < code_length; j++)
				{
					bytes[j] = data[++currIndex];
				}
				System.out.println("字节码: " + encode(bytes, code_length));
				
				int exception_table_length = getInt();
				System.out.println("code exception_table(" + exception_table_length + "):");
				for (int j = 0; j < exception_table_length; j++)
				{
					loadException();
				}
				int attributes_count = getInt();
				System.out.println("code attributes(" + attributes_count + "):");
				for (int j = 0; j < attributes_count; j++)
				{
					System.out.println("code attributes #" + j + ":");
					loadAttributes();
				}
			}
			else if (attribute_name.equals("ConstantValue"))
			{
				System.out.println("ConstantValue:");
				int constantvalue_index = getInt();
				System.out.println("attribute_length(===2): " + attribute_length + ",constantvalue_index: " + constantvalue_index);
				
			}
			else if (attribute_name.equals("Deprecated"))
			{
				System.out.println("Deprecated:");
				System.out.println("attribute_length(===0): " + attribute_length);
			}
			else if (attribute_name.equals("Exceptions"))
			{
				System.out.println("Exceptions:");
				int number_of_exceptions = getInt();
				for(int j = 0; j < number_of_exceptions; j++)
				{
					int exception_index = getInt();
					System.out.println(getClassName(exception_index));
				}
			}
			else if (attribute_name.equals("InnerClasses"))
			{
				System.out.println("InnerClasses:");
				int number_of_classes = getInt();
				for(int j = 0; j < number_of_classes; j++)
				{
					int inner_class_info_index = getInt();
					int outer_class_info_index = getInt();
					int inner_name_index = getInt();
					int inner_class_access_flags = getInt();
					System.out.println("inner_class_info: " + getClassName(inner_class_info_index) +
									   "outer_class_info: " + getClassName(outer_class_info_index) +
									   "inner_name: " + getString(inner_name_index) +
									   "inner_class_access: " + getAccessFlag(inner_class_access_flags));
				}
			}
			else if (attribute_name.equals("LineNumberTable"))
			{				
				int line_number_table_length = getInt();
				System.out.println("LineNumberTable(" + line_number_table_length + "):");
				
				for(int j = 0; j < line_number_table_length; j++)
				{
					int start_pc = getInt();
					int line_number = getInt();
					System.out.println("  #" + j + ". line " + line_number + " : " + start_pc);
				}
			}
			else if (attribute_name.equals("LocalVariableTable"))
			{
				System.out.println("LocalVariableTable:");
				int local_variable_table_length = getInt();
				for(int j = 0; j < local_variable_table_length; j++)
				{
					int start_pc = getInt();
					int length = getInt();
					int name_index = getInt();
					int descriptor_index = getInt();
					int index = getInt();
					System.out.println("start_pc: " + start_pc + ", length: " + length +
									   "name: " + getString(name_index) + ", descriptor: " + getString(descriptor_index) +
									   "index: " + index);
					
				}
			}
			else if (attribute_name.equals("SourceFile"))
			{
				System.out.println("SourceFile:");
				int sourcefile_index = getInt();
				System.out.println("sourcefile: " + getString(sourcefile_index));
			}
			else if (attribute_name.equals("Synthetic"))
			{
				System.out.println("Synthetic:");
			}
			else if (attribute_name.equals("StackMapTable"))
			{				
				int number_of_entries = getInt();
				System.out.println("StackMapTable(" + number_of_entries + "):");
				
				for (int j = 0; j < number_of_entries; j++)
				{
					loadStackMapTable();
				}				
			}
			else
			{
				System.out.println("未定义的属性: " + attribute_name);
			}
				
		}
	}
	
	private static void loadStackMapTable() throws Exception
	{
		int frame_type = toInt(data[++currIndex]);
		if (frame_type < 0)
		{
			throw new Exception("error frame_type:" + frame_type);
		}
		else if (frame_type < 64)//(0-63)
		{
			System.out.println("SAME");
		}
		else if (frame_type < 128)//(64-127)
		{
			System.out.println("SAME_LOCALS_1_STACK_ITEM");
			verification_type();
		}
		else if (frame_type < 247)//(128-246 reserved)
		{
			System.out.println("reserved");			
		}
		else if (frame_type < 248) //247
		{
			System.out.println("SAME_LOCALS_1_STACK_ITEM_EXTENDED");
			int offset_delta = getInt();
			System.out.println("offset_delta = " + offset_delta);
			verification_type();
		}
		else if (frame_type < 251) //248-250
		{
			System.out.println("CHOP");
			int offset_delta = getInt();
			System.out.println("offset_delta = " + offset_delta);		
		}
		else if (frame_type < 252) //251
		{
			System.out.println("SAME_FRAME_EXTENDED");
			int offset_delta = getInt();
			System.out.println("offset_delta = " + offset_delta);
		}
		else if (frame_type < 255) //252-254
		{
			System.out.println("APPEND");
			int offset_delta = getInt();
			System.out.println("offset_delta = " + offset_delta);
			int num = frame_type  - 251;
			for (int j = 0; j < num; j++)
			{
				verification_type();
			}
		}
		else if (frame_type < 256)//255
		{
			System.out.println("FULL_FRAME");
			int offset_delta = getInt();
			System.out.println("offset_delta = " + offset_delta);
			
			int number_of_locals = getInt();
			for (int j = 0; j < number_of_locals; j++)
			{
				verification_type();
			}
			int number_of_stack_items = getInt();
			for (int j = 0; j < number_of_stack_items; j++)
			{
				verification_type();
			}
		}
	}
	private static void verification_type()
	{
		int tag = getInt(1);
		if (tag == 0)
		{
			System.out.println("ITEM_Top");	
		}
		else if (tag == 1)
		{
			System.out.println("ITEM_Integer");	
		}
		else if (tag == 2)
		{
			System.out.println("ITEM_Float");	
		}	
		else if (tag == 3)
		{
			System.out.println("ITEM_Double");	
		}
		else if (tag == 4)
		{
			System.out.println("ITEM_Long");	
		}
		else if (tag == 5)
		{
			System.out.println("ITEM_Null");	
		}
		else if (tag == 6)
		{
			System.out.println("ITEM_UninitializedThis");	
		}
		else if (tag == 7)
		{
			int cpool_index = getInt();
			System.out.println("ITEM_Object: cpool_index = " + cpool_index);			
		}
		else if (tag == 8)
		{
			int offset = getInt();
			System.out.println("ITEM_Uninitialized: offset = " + offset);			
		}
		else
		{
			System.out.println("verification_type error: tag = " + tag);
		}
	}
	private static void loadException()
	{
		int start_pc = getInt();
		int end_pc = getInt();
		int handler_pc = getInt();
		int catch_type_index = getInt();
		System.out.println("start_pc: " + start_pc + ", end_pc: " + end_pc + ",handler_pc: " + handler_pc + ",catch_type:" + getClassName(catch_type_index));
	}
	private static String encode(byte[] buffer,int length) 
    {     
        StringBuffer sb = new StringBuffer();
        //将字节数组中每个字节拆解成2位16进制整数 
        for(int i=0;i<length;i++) 
        { 
            sb.append(hexString.charAt((buffer[i]&0xf0)>>4)); 
            sb.append(hexString.charAt(buffer[i]&0x0f));             
        } 
        return sb.toString(); 
    } 
	private static String getAccessFlag(int af)
	{
		StringBuffer sb = new StringBuffer();
		
		if ((ACC_PUBLIC & af) != 0){	sb.append("ACC_PUBLIC,");}
		if ((ACC_PRIVATE & af) != 0){	sb.append("ACC_PRIVATE,");}
		if ((ACC_PROTECTED & af) != 0){ sb.append("ACC_PROTECTED,");}
		if ((ACC_STATIC & af) != 0){	sb.append("ACC_STATIC,");}
		if ((ACC_FINAL & af) != 0){		sb.append("ACC_FINAL,");}
		if ((ACC_SUPER & af) != 0){		sb.append("ACC_SUPER,");}
		if ((ACC_SYNCHRONIZED & af) != 0){sb.append("ACC_SYNCHRONIZED,");}		
		if ((ACC_VOLATILE & af) != 0){	sb.append("ACC_VOLATILE,");}
		if ((ACC_TRANSIENT & af) != 0){	sb.append("ACC_TRANSIENT,");}
		if ((ACC_NATIVE & af) != 0){	sb.append("ACC_NATIVE,");}		
		if ((ACC_INTERFACE & af) != 0){	sb.append("ACC_INTERFACE,");}
		if ((ACC_ABSTRACT & af) != 0){	sb.append("ACC_ABSTRACT,");}
		if ((ACC_STRICT & af) != 0){	sb.append("ACC_STRICT,");}
		return sb.toString();
	}
	//打印常量池
	private static void printCp()
	{
		for (int i = 0; i < cp.length; i++)
		{
			if (cp[i] != null)
			{
				System.out.print("const #" + i + " = " + cp[i].type + "\t" + cp[i].value );
				if (cp[i].type.equals("class") || cp[i].type.equals("String"))
				{
					System.out.println("\t//" + getClassName(i));
				}
				else if (cp[i].type.equals("Field") || cp[i].type.equals("Mehtod") || cp[i].type.equals("InterfaceMethod"))
				{					
					String [] s = cp[i].value.replaceAll("#", "").split("\\.");
					
					System.out.println("\t//" + getClassName(Integer.parseInt(s[0])) + "." + getNameAndType(Integer.parseInt(s[1])));
				}
				else if (cp[i].type.equals("NameAndType"))
				{
					System.out.println("\t//" + getNameAndType(i));
				}				
				else
				{
					System.out.println();
				}
			}
		}
	}
	private static String getString(int index)
	{
		if (index > 0 && index < cp.length)
		{
			return cp[index].value;
		}
		else
		{
			return "不在常量池范围内！";
		}
	}
	private static String getClassName(int index)
	{
		int i = toInt(cp[index].value.replace("#", ""));
		return cp[i].value;
	}
	private static String getNameAndType(int index)
	{
		String [] s = cp[index].value.replaceAll("#", "").split(":");
		return cp[toInt(s[0])].value + ":" + cp[toInt(s[1])].value;
	}	
	
	private static boolean loadConstanPool(byte[] data, int index) throws Exception
	{
		boolean step = false;
		int tag = data[++currIndex];
		int name_index, class_index, name_and_type_index;
		byte [] bytes;
		switch(tag)
		{
			case CONSTANT_Utf8:
				int length = getInt();
				bytes = new byte [length];
				for (int i = 0; i < length; i++)
				{
					bytes[i] = data[++currIndex];
				}
				cp[index] = new ConstantPool("Asciz", new String(bytes, "UTF-8"));
				break;
			case CONSTANT_Integer:
				int integer = getInt(4);
				cp[index] = new ConstantPool("Integer", String.valueOf(integer));
				break;
			case CONSTANT_Float:					
				int int_float = getInt(4);
				cp[index] = new ConstantPool("Float", new String(Float.intBitsToFloat(int_float) + "f"));
				break;
			case CONSTANT_Long:
				long high_bytes = getInt(4);
				long low_bytes = getInt(4);
				long l = ((long)high_bytes << 32) + low_bytes;
				cp[index] = new ConstantPool("Long", new String(l + "l"));
				step = true;
				break;
			case CONSTANT_Double:
				int int_double_1 = getInt(4);
				int int_double_2 = getInt(4);
				long long_double = ((long)int_double_1 << 32) + int_double_2;				
				cp[index] = new ConstantPool("Float", new String(Double.longBitsToDouble(long_double) + "d"));
				step = true;
				break;
			case CONSTANT_Class:
				name_index = getInt();
				cp[index] = new ConstantPool("class", new String("#" + name_index));
				break;
			case CONSTANT_String:
				int string_index = getInt();
				cp[index] = new ConstantPool("String", new String("#" + string_index));
				break;
			case CONSTANT_Fieldref:
				class_index = getInt();
				name_and_type_index = getInt();
				cp[index] = new ConstantPool("Field", new String("#" + class_index + ".#" + name_and_type_index));
				break;
			case CONSTANT_Methodref:
				class_index = getInt();
				name_and_type_index = getInt();
				cp[index] = new ConstantPool("Mehtod", new String("#" + class_index + ".#" + name_and_type_index));
				break;
			case CONSTANT_InterfaceMethodref:
				class_index = getInt();
				name_and_type_index = getInt();
				cp[index] = new ConstantPool("InterfaceMethod", new String("#" + class_index + ".#" + name_and_type_index));
				break;
			case CONSTANT_NameAndType:
				name_index = getInt();
				int descriptor_index = getInt();
				cp[index] = new ConstantPool("NameAndType", new String("#" + name_index + ":#" + descriptor_index));
				break;
			default:
				System.out.println("error tag: " + tag);
		}
		return step;
	}
}
