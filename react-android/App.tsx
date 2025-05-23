import React, { useEffect } from 'react';
import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import Home from './src/app/pages/Home';
import SignUp from './src/app/pages/SignUp';
import Login from './src/app/pages/Login';
import { GluestackUIProvider } from '@gluestack-ui/themed';
import { SafeAreaProvider } from 'react-native-safe-area-context';


const Stack = createNativeStackNavigator()
function App(): React.JSX.Element{
    return(
        <SafeAreaView>
            <GlueStackUIProvider>
                <NavigationContainer>
                    <Stack.Navigator>
                        <Stack.Screen name = "Login" comoponent = {Login}/>
                        <Stack.Screen name = "SignUp" component = {SignUp}/>
                        <Stack.Screen name = "Home" component = {Home}/>
                    </Stack.Navigator>
                </NavigationContainer>
            </GlueStackUIProvider>
        </SafeAreaView>
    );
}

export default App;