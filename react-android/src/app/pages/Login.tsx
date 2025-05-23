import {View, Text, StyleSheet} from 'react-native';
import React, {useEffect, useState} from 'react';
import CustomText from '../components/CustomText';
import CustomBox from '../components/CustomBox';
import {GestureHandlerRootView, TextInput} from 'react-native-gesture-handler';
import {Button, ButtonText} from '@gluestack-ui/themed';
import AsyncStorage from '@react-native-async-storage/async-storage';
import LoginService from '../api/LoginService';


const Login = ({navigation}) => {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');

    const SignUp = () => {
        navigation.navigate('SignUp', {name: 'SignUp'});
    }

  return (
    <View style = {styles.loginContainer}>
      <CustomBox style = {loginBox}>
        <CustomText style = {styles.heading}>Login</CustomText>
        <TextInput
            placeholder="User Name"
            value={username}
            onChangeText={text => setUserName(text)}
            style={styles.textInput}
            placeholderTextColor="#888"
        />
        <TextInput
            placeholder="Password"
            value={password}
            onChangeText={text => setPassword(text)}
            style={styles.textInput}
            placeholderTextColor="#888"
            secureTextEntry
        />
      </CustomBox>
        <Button onPressIn = {() => Login()} style = {styles.button}>
            <CustomBox style = {buttonBox}>
                <CustomText style = {{textAlign: 'center'}}>Login</CustomText>
            </CustomBox>
        </Button>
        <Button onPressIn = {() => SignUp()} style = {styles.button}>
            <CustomBox style = {buttonBox}>
                <CustomText style = {{textAlign: 'center'}}>SignUp</CustomText>
            </CustomBox>
        </Button>
    </View>
  );
};

export default Login;

const loginBox = {
    mainBox: {
        backgroundColor: "#fff",
        borderColor: 'black',
        borderWidth: 1,
        borderRadius: 1,
        padding: 20,
    },
    shadowBox: {
        backgroundColor: 'gray',
        borderRadius: 10,
    },
};

const buttonBox = {
    mainBox: {
        backgroundColor: "#fff",
        borderColor: "black",
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
    loginContainer: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        padding: 20,
    },
    button: {
        marginTop: 20,
        width: '30%',
    },
    heading: {
        fontSize: 24,
        fontWeight: 'bold',
        marginBottom: 20,
    },
    textInput:{
        backgroundColor: '#f0f0f0',
        borderRadius: 5,
        padding: 10,
        marginBottom: 10,
        width: '100%',
        color: 'black',
    },
});