def main():
    encrypted = encrypt("bad", "thequickbrownfoxjumpsoverthelazydog")
    decrypted = decrypt("bad", encrypted)
    if (decrypted == "thequickbrownfoxjumpsoverthelazydog"):
        print("WOOO")

    encrypted = encrypt(
        "helo", "fsdfhsdakfhsdakfioegfdsbkfhaskduoeqwhfuidhsakfjlhdsklfiutkldfsglk")
    decrypted = decrypt("helo", encrypted)
    if (decrypted == "fsdfhsdakfhsdakfioegfdsbkfhaskduoeqwhfuidhsakfjlhdsklfiutkldfsglk"):
        print("WOOO")


def encrypt(key: str, message: str):
    # transform the key and message to lowercase to be easier to work with
    key = key.lower()
    message = message.lower()

    # sort the key alphabetically
    sorted_key = ''.join(sorted(key))
    encrypted_message = [None] * (len(message) + len(key))

    # we parse the message in bits equal to the length of the key
    for i in range(int(len(message) / len(key) + 1)):
        # for each of those characters...
        for j in range(len(key)):
            if (i * len(key) + j == len(message)):
                break
            # we move it to a corresponding place in the encrypted message
            # according to the index of the corresponding key character in the
            # sorted key
            encrypted_message[i * len(key) + sorted_key.index(key[j])
                              ] = message[i * len(key) + j]

    # cleanup the values and convert the encrypted message to a nice string
    encrypted_message = list(map(
        lambda x: '' if x == None else x, encrypted_message))
    encrypted_message = ''.join(encrypted_message)

    return encrypted_message


def decrypt(key: str, message: str):
    # this is exactly the same as the encryption, except for...
    key = key.lower()
    message = message.lower()

    sorted_key = ''.join(sorted(key))
    decrypted_message = [None] * (len(message) + len(key))

    for i in range(int(len(message) / len(key) + 1)):
        for j in range(len(key)):
            if (i * len(key) + j == len(message)):
                break
            # here, where i reverse the key and the sorted key
            # look for the index of the sorted key char corresponding to our
            # character in the message, and put it at an index corresponding
            # to the original, unsorted key
            decrypted_message[i * len(key) + key.index(sorted_key[j])
                              ] = message[i * len(key) + j]

    decrypted_message = list(map(
        lambda x: '' if x == None else x, decrypted_message))
    decrypted_message = ''.join(decrypted_message)

    return decrypted_message


if __name__ == "__main__":
    # lets go
    main()
