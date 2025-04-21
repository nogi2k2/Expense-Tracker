package authservice.serializer;

import authservice.model.UserInfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import java.util.Map;

public class UserInfoSerializer implements Serializer<UserInfoDto>{
	@Override
	public void configure(Map<String, ?> map, boolean b){}

	@Override
	public void close(){}

	@Override
	public byte[] serialize(String arg0, UserInfoDto userInfoDto){
		byte[] retVal = null;
		ObjectMapper objMapper = new ObjectMapper();

		try{
			retVal = objMapper.writeValueAsString(userInfoDto).getBytes();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
		return retVal;
	}
}
