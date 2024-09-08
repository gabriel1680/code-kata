import unittest
from src.validate_password import *

class TestPasswordValidator1(unittest.TestCase):

    def setUp(self) -> None:
        self.validate_password = create_password_validator("1")

    def test_password_should_not_be_ok(self):
        invalid_passwords = ["Xxxxx1_", "Xxxxxxx1", "xxxxxx1_", "XXXXX1_"]
        for password in invalid_passwords:
            self.assertFalse(self.validate_password(password))

    def test_password_should_be_ok(self):
        password = "Xxxxxx1_"
        self.assertTrue(self.validate_password(password))


class TestPasswordValidator2(unittest.TestCase):

    def setUp(self) -> None:
        self.validate_password = create_password_validator("2")

    def test_password_should_not_be_ok(self):
        invalid_passwords = ["Xxxx1", "xxxxx1", "XXXXX1", "Xxxxxx"]
        for password in invalid_passwords:
            self.assertFalse(self.validate_password(password))

    def test_password_should_be_ok(self):
        password = "Xxxxx1"
        self.assertTrue(self.validate_password(password))


class TestPasswordValidator3(unittest.TestCase):

    def setUp(self) -> None:
        self.validate_password = create_password_validator("3")

    def test_password_should_not_be_ok(self):
        invalid_passwords = ["Xxxxxxxxxxxxxx_", "xxxxxxxxxxxxxxx_", "Xxxxxxxxxxxxxxx", "XXXXXXXXXXXXXXX_"]
        for password in invalid_passwords:
            self.assertFalse(self.validate_password(password))


    def test_password_should_be_ok(self):
        password = "Xxxxxxxxxxxxxxx_"
        self.assertTrue(self.validate_password(password))


if __name__ == "__main__":
    unittest.main()

