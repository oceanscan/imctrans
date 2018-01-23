from unittest import TestCase

import imctrans.java.code


class TestJavaCode(TestCase):
    def test_underscore_to_camelcase(self):
        self.assertEqual(imctrans.java.code.under_to_camel('x'), 'x')
        self.assertEqual(imctrans.java.code.under_to_camel('get_x'), 'getX')

    def test_camelcase_to_underscore(self):
        self.assertEqual(imctrans.java.code.camel_to_under('OP'), 'op')
        self.assertEqual(imctrans.java.code.camel_to_under('ZUnits'), 'z_units')
        self.assertEqual(imctrans.java.code.camel_to_under('SpeedUnits'), 'speed_units')
