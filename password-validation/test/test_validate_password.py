import unittest
from src.validate_password import *

class TestPasswordValidator(unittest.TestCase):

    def setUp(self) -> None:
        self.rules = all_rules

    def test_password_should_not_be_ok(self):
        invalid_passwords = ["Xxxxx1_", "Xxxxxxx1", "xxxxxx1_", "XXXXX1_"]
        for password in invalid_passwords:
            self.assertFalse(validate_password(password, self.rules))

    def test_password_should_be_ok(self):
        password = "Xxxxxx1_"
        self.assertTrue(validate_password(password))


class TestPasswordValidator2(unittest.TestCase):

    def setUp(self) -> None:
        self.rules = simple_rules

    def test_password_should_not_be_ok(self):
        invalid_passwords = ["Xxxx1", "xxxxx1", "XXXXX1", "Xxxxxx"]
        for password in invalid_passwords:
            self.assertFalse(validate_password(password, self.rules))

    def test_password_should_be_ok(self):
        password = "Xxxxx1"
        self.assertTrue(validate_password(password, self.rules))


class TestPasswordValidator3(unittest.TestCase):

    def setUp(self) -> None:
        self.rules = mixed_rules

    def test_password_should_not_be_ok(self):
        invalid_passwords = ["Xxxxxxxxxxxxxx_", "xxxxxxxxxxxxxxx_", "Xxxxxxxxxxxxxxx", "XXXXXXXXXXXXXXX_"]
        for password in invalid_passwords:
            self.assertFalse(validate_password(password, self.rules))


    def test_password_should_be_ok(self):
        password = "Xxxxxxxxxxxxxxx_"
        self.assertTrue(validate_password(password, self.rules))


if __name__ == "__main__":
    unittest.main()

