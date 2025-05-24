import {View, Text, StyleSheet} from 'react-native';
import React, {useState} from 'react';
import CustomText from '../components/CustomText';
import CustomBox from '../components/CustomBox';
import {GestureHandlerRootView, TextInput} from 'react-native-gesture-handler';
import {Button, ButtonText} from '@gluestack-ui/themed';
import AsyncStorage from '@react-native-async-storage/async-storage';

const SignUp = (navigation) => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');

    const SignUp = async () => {
        try{
            const response = await fetch("http://localhost:9898/auth/v1/singup", {
                method: 'POST',
                headers: {
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                    'X-Requested-With': 'XMLHttpRequest',
                },
                body: JSON.stringify({
                    'first_name': firstName,
                    'last_name': lastName,
                    'email': email,
                    'user_name': userName,
                    'password': password,
                    'phone_number': phoneNumber,
                }),
            });
            if (response.ok){
                const data = await response.json();
                await AsyncStorage.setItem("accessToken", data['accessToken']);
                await AsyncStorage.setItem("token", data['token']);
                navigation.navigate('Home', {name: 'Home'});
            }
        }catch (error){
            console.error('Error during sign up:', error);
        }
    }

    const Login = () => {
        navigation.navigate('Login', {name: 'Login'});
    }

  return (
    <View style = {styles.signupContainer}>
        <CustomBox style = {singupBox}>
            <CustomText style = {styles.heading}>SignUp</CustomText>
            <TextInput 
                placeholder="First Name"
                value={firstName}
                onChangeText={text => setFirstName(text)}
                style = {styles.textInput}
                palceholderTextColor="#888"
            />
            <TextInput 
                placeholder="Last Name"
                value={lastName}
                onChangeText={text => setLastName(text)}
                style = {styles.textInput}
                palceholderTextColor="#888"
            />
            <TextInput 
                placeholder="Email"
                value={email}
                onChangeText={text => setEmail(text)}
                style = {styles.textInput}
                palceholderTextColor="#888"
                keyboardType="email-address"
            />
            <TextInput 
                placeholder="User Name"
                value={userName}
                onChangeText={text => setUserName(text)}
                style = {styles.textInput}
                palceholderTextColor="#888"
            />
            <TextInput 
                placeholder="password"
                value={password}
                onChangeText={text => setPassword(text)}
                style = {styles.textInput}
                palceholderTextColor="#888"
                secureTextEntry
            />
            <TextInput 
                placeholder="Phone Number"
                value={phoneNumber}
                onChangeText={text => setPhoneNumber(text)}
                style = {styles.textInput}
                palceholderTextColor="#888"
                keyboardType="phone-pad"
            />
        </CustomBox>
        <Button onPressIn = {() => SignUp()} style = {styles.button}>
            <CustomBox style={buttonBox}>
                <CustomText style = {{textAlign: 'center'}}>SignUp</CustomText>
            </CustomBox>
        </Button>
        <Button onPressIn = {() => Login()} style = {styles.button}>
            <CustomBox style = {buttonBox}>
                <CustomText style = {{textAlign: 'center'}}>Login</CustomText>
            </CustomBox>
        </Button>
    </View>
  );
};

const signupBox = {
    mainBox: {
        backgroundColor: "#fff",
        borderColor: 'black',
        borderWidth: 1,
        borderRadius: 10,
        padding: 20,
    },
    shadowBox: {
        backgroundColor: 'gray',
        borderRadius: 10,
    },
};

const buttonBox = {
    mainBox: {
        backgroundColor: '#fff',
        borderColor: 'black',
        borderWidth: 1,
        borderRadius: 10,
        padding: 10,
    },
    shadowBox: {
        backgroundColor: 'gray',
        borderRadius: 10,
    },
};

const styles = StyleSheet.create({
    heading: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
    button: {
        marginTop: 20,
        width: "30%",
    },
    textInput: {
        backgroundColor: "#f0f0f0",
        borderRadius: 5,
        padding: 10,
        marginBottom: 10,
        width: '100%',
        color: 'black',
    },
    signupContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20,
    }
});

export default SignUp;