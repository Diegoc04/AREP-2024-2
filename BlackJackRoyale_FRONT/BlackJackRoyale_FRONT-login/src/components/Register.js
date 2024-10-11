import React, { useState } from 'react';
import axios from 'axios';

const Register = () => {
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');
    const [showPassword, setShowPassword] = useState(false);

    // Función para validar la contraseña
    const validatePassword = (password) => {
        const passwordRegex = /^(?=.*[!@#$%^&*()_\-+=.;,#<>°|])(?=.*[0-9].*[0-9]).{8,}$/;
        return passwordRegex.test(password);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Validación de la contraseña
        if (!validatePassword(password)) {
            setMessage('La contraseña debe tener al menos 8 caracteres, incluir al menos un carácter especial y al menos dos números.');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/blackjack/register', {
                email,
                name,
                password,
            });
            setMessage(response.data);
            setEmail('');
            setName('');
            setPassword('');
        } catch (error) {
            console.error('There was an error registering the user!', error);
            setMessage('Error registering user');
        }
    };

    // Estilos en línea mejorados para centrar y embellecer la UI
    const styles = {
        container: {
            maxWidth: '400px',
            margin: '50px auto',
            padding: '20px',
            borderRadius: '10px',
            backgroundColor: '#f0f4f8',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.2)',
            textAlign: 'center',
        },
        title: {
            textAlign: 'center',
            marginBottom: '20px',
            color: '#333',
            fontSize: '24px',
            fontWeight: 'bold',
        },
        form: {
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
        },
        label: {
            marginBottom: '5px',
            color: '#555',
            fontWeight: 'bold',
        },
        input: {
            width: '100%',
            padding: '10px',
            marginBottom: '15px',
            border: '1px solid #ccc',
            borderRadius: '5px',
            fontSize: '16px',
            outline: 'none',
            boxSizing: 'border-box',
        },
        checkboxContainer: {
            display: 'flex',
            alignItems: 'center',
            marginBottom: '15px',
        },
        checkboxLabel: {
            marginLeft: '5px',
            color: '#555',
        },
        button: {
            padding: '10px 20px',
            backgroundColor: '#4CAF50',
            color: 'white',
            border: 'none',
            borderRadius: '5px',
            cursor: 'pointer',
            fontSize: '16px',
            transition: 'background-color 0.3s ease',
            width: '100%',
        },
        buttonHover: {
            backgroundColor: '#45a049',
        },
        message: {
            marginTop: '20px',
            color: message.startsWith('Error') ? '#f00' : '#4CAF50',
        },
    };

    return (
        <div style={styles.container}>
            <h2 style={styles.title}></h2>
            <form style={styles.form} onSubmit={handleSubmit}>
                <div style={{ width: '100%' }}>
                    <label style={styles.label}>Correo Electrónico:</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                        style={styles.input}
                    />
                </div>
                <div style={{ width: '100%' }}>
                    <label style={styles.label}>Nombre de usuario:</label>
                    <input
                        type="text"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                        style={styles.input}
                    />
                </div>
                <div style={{ width: '100%' }}>
                    <label style={styles.label}>Contraseña:</label>
                    <input
                        type={showPassword ? 'text' : 'password'}
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                        style={styles.input}
                    />
                </div>
                <div style={styles.checkboxContainer}>
                    <input
                        type="checkbox"
                        checked={showPassword}
                        onChange={(e) => setShowPassword(e.target.checked)}
                    />
                    <label style={styles.checkboxLabel}>Mostrar contraseña</label>
                </div>
                <button
                    type="submit"
                    style={styles.button}
                >
                    Register
                </button>
            </form>
            {message && <p style={styles.message}>{message}</p>}
        </div>
    );
};

export default Register;

