from unittest import TestCase

import imctrans.java.code


class TestJavaCode(TestCase):
    def test_underscore_to_camelcase(self):
        self.assertEqual(imctrans.java.code.underscore_to_camelcase('x'), 'x')
        self.assertEqual(imctrans.java.code.underscore_to_camelcase('get_x'), 'getX')

    def test_camelcase_to_underscore(self):
        self.assertEqual(imctrans.java.code.camelcase_to_underscore('OP'), 'op')
        self.assertEqual(imctrans.java.code.camelcase_to_underscore('ZUnits'), 'z_units')
        self.assertEqual(imctrans.java.code.camelcase_to_underscore('SpeedUnits'), 'speed_units')
